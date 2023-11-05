package com.doruk.parakeet.Security

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import com.doruk.parakeet.ParakeetUser.ParakeetUserRegisterDTO
import com.doruk.parakeet.ParakeetUser.ParakeetUserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This controller handles login and register requests.
 * Both routes are public as specified in SecurityConfig.
 */
@RestController
@RequestMapping("/auth")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: ParakeetUserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: ParakeetUserRegisterDTO): LoginResponseDto {
        val user = userService.getUser(payload.username) ?: throw Exception("Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw Exception("Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: ParakeetUserRegisterDTO): LoginResponseDto {
        if (userService.getUser(payload.username) != null) {
            throw Exception("Name already exists")
        }

        val user = ParakeetUser(
            username = payload.username,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.createUser(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}