package com.sorsix.gopbackend.model.dto

import com.sorsix.gopbackend.api.validation.PasswordMatchConstraint
import com.sorsix.gopbackend.api.validation.UniqueUsernameConstraint
import com.sorsix.gopbackend.api.validation.ValidPasswordConstraint

@PasswordMatchConstraint
data class UserRegisterDto(
    @UniqueUsernameConstraint
    val username: String,
    @ValidPasswordConstraint
    val password: String,
    val confirmPassword: String,
    val country: String
)
