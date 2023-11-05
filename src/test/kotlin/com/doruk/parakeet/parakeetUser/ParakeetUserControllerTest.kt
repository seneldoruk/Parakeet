package com.doruk.parakeet.parakeetUser


import com.doruk.parakeet.security.AuthController
import com.doruk.parakeet.testClasses.TestBaseWithDB
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ParakeetUserControllerTest : TestBaseWithDB(
) {
    @Autowired
    lateinit var controller: ParakeetUserController

    @Autowired
    lateinit var authController: AuthController

    @Autowired
    lateinit var repository: ParakeetUserRepository

    @Autowired
    lateinit var userService: ParakeetUserService


    val user1 = ParakeetUser("abc", "123")
    val user2 = ParakeetUser("xyz", "123")

    @Test
    fun create_and_get_user() {
        //controller saves the user
        val postBody = jacksonObjectMapper().writeValueAsString(user1)
        mockMvc.perform(
            post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody)
        )
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())


        //saved correct user
        val repoRes = repository.findById(user1.username).get()
        assertEquals(repoRes, user1)
        repository.delete(repoRes)
    }

    @Test
    @Transactional
    fun user_can_follow() {

        authController.register(ParakeetUserRegisterDTO(user1.username, user1.password))
        authController.register(ParakeetUserRegisterDTO(user2.username, user2.password))
        val token = login(user1)

        mockMvc.perform(
            post("/users/follow/xyz").header("Authorization", "Bearer $token")
        )


        val user1FromDB = controller.getUser("abc")
        val user2FromDB = controller.getUser("xyz")
        if (user1FromDB == null || user2FromDB == null) {
            throw Exception("Cant parse users from DB")
        }

        //user1 is following user2
        assert(user2FromDB.followers.contains(user1FromDB))
        assert(user1FromDB.following.contains(user2FromDB))

        //user2 isn't following user1
        assert(!user2FromDB.following.contains(user1FromDB))
        assert(!user1FromDB.followers.contains(user2FromDB))

        userService.deleteUser(user1FromDB)
        userService.deleteUser(user2FromDB)
    }
}