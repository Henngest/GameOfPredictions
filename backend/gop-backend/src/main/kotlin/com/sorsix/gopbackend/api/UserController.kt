package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.repository.UserRepository
import com.sorsix.gopbackend.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/profile")
class UserController(val userRepository: UserRepository,
val userService: UserService) {

    @GetMapping
    fun showUserDetails(@RequestParam username: String): User? =
            this.userRepository.findByUsername(username)

    @PutMapping("/edit")
    fun editUserDetails(@RequestParam username: String, @RequestParam country: String): User =
            this.userService.editUserDetails(username, country)
}