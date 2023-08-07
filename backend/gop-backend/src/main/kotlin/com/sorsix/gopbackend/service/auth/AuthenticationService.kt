package com.sorsix.gopbackend.service.auth

import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.model.dto.AuthenticationDto
import com.sorsix.gopbackend.model.dto.UserLoginDto
import com.sorsix.gopbackend.model.dto.UserRegisterDto
import com.sorsix.gopbackend.model.enumerations.Role
import com.sorsix.gopbackend.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(userRegisterDto: UserRegisterDto): AuthenticationDto {
        var user = User(
            username = userRegisterDto.username,
            password = passwordEncoder.encode(userRegisterDto.password),
            country = userRegisterDto.country,
            role = Role.ROLE_USER,
            rating = 0.00
        )
        user = this.userRepository.save(user)

        val jwtToken = jwtService
            .generateToken(
                userDetails = user,
                extraClaims = mapOf("roles" to user.authorities.map { it.authority })
            )
        return AuthenticationDto(jwtToken)
    }

    fun authenticate(userLoginDto: UserLoginDto): AuthenticationDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userLoginDto.username,
                userLoginDto.password
            )
        );

        val user = this.userRepository.findByUsername(userLoginDto.username)
        val jwtToken = jwtService.generateToken(
            userDetails = user!!,
            extraClaims = mapOf("roles" to user.authorities.map { it.authority })
        )
        return AuthenticationDto(jwtToken)
    }
}