package com.doruk.parakeet.TestClasses

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.function.Supplier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc

class TestWithDB {
    @Autowired
    lateinit var mockMvc: MockMvc

    fun login(authBody: ParakeetUser): String {
        val postBody = jacksonObjectMapper().writeValueAsString(authBody)
        return mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody)
        ).andReturn().response.contentAsString.substring(10, 161)
    }

    fun register(authBody: ParakeetUser): String {
        val postBody = jacksonObjectMapper().writeValueAsString(authBody)
        return mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody)
        ).andReturn().response.contentAsString.substring(10, 161)
    }

    companion object {
        @Container
        val postgres = PostgreSQLContainer("postgres:14.1-alpine")

        @JvmStatic
        @DynamicPropertySource
        fun configureTestContainerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.jpa.hibernate.ddl-auto", Supplier { -> "create" })
        }
    }
}