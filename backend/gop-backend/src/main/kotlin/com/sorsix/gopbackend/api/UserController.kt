package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun showUserDetails(@RequestParam username: String): User? =
            this.userRepository.findByUsername(username)
}