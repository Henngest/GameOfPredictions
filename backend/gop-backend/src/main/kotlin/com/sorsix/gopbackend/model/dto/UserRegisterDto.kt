package com.sorsix.gopbackend.model.dto

data class UserRegisterDto(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val country: String
)
