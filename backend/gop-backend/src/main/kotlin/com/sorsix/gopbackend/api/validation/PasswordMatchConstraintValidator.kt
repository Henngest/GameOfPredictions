package com.sorsix.gopbackend.api.validation

import com.sorsix.gopbackend.model.dto.UserRegisterDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordMatchConstraintValidator: ConstraintValidator<PasswordMatchConstraint, UserRegisterDto> {
    override fun isValid(userRegisterDto: UserRegisterDto, context: ConstraintValidatorContext): Boolean =
        userRegisterDto.password == userRegisterDto.confirmPassword
}