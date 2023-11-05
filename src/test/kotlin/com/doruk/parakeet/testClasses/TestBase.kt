package com.doruk.parakeet.testClasses

import com.doruk.parakeet.parakeetUser.ParakeetUser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class TestBase {
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

}