package com.doruk.parakeet.testClasses

import com.doruk.parakeet.parakeetUser.ParakeetUser
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestBaseWithDB {
    @Autowired
    lateinit var mockMvc: MockMvc


    object SingletonContainer {
        val postgres = PostgreSQLContainer("postgres:14.1-alpine")

        init {
            postgres.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun configureTestContainerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create" }
        }
    }

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

}