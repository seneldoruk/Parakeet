package com.doruk.parakeet.ParakeetUser

import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController()
@RequestMapping("users")
class ParakeetUserController(private val userService: ParakeetUserService) {
    @GetMapping("{username}")
    fun getUser(@PathVariable username: String): ParakeetUser? {
        return userService.getUser(username)
    }


    @PostMapping("follow/{username}")
    fun followUser(@PathVariable username: String, principal: Principal) {
        userService.followUser(principal.name, username)
    }
}