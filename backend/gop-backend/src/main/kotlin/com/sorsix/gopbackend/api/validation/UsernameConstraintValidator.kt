package com.sorsix.gopbackend.api.validation

import com.sorsix.gopbackend.repository.UserRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UsernameConstraintValidator(
    private val userRepository: UserRepository
) : ConstraintValidator<UniqueUsernameConstraint, String> {

    override fun isValid(username: String, context: ConstraintValidatorContext): Boolean {
        return this.userRepository.findByUsername(username) == null
    }
}