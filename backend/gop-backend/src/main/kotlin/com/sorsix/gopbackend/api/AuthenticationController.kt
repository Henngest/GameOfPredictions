package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.dto.AuthenticationDto
import com.sorsix.gopbackend.model.dto.UserLoginDto
import com.sorsix.gopbackend.model.dto.UserRegisterDto
import com.sorsix.gopbackend.service.auth.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@Validated @RequestBody userRegisterDto: UserRegisterDto): ResponseEntity<AuthenticationDto> {
        val v = authenticationService.register(userRegisterDto)
        return ResponseEntity.ok(v)
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginDto: UserLoginDto): ResponseEntity<AuthenticationDto> {
        return ResponseEntity.ok(authenticationService.authenticate(userLoginDto))
    }
}