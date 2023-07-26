package com.sorsix.gopbackend.api.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.passay.*
import java.util.*
import java.util.stream.Collectors


class PasswordConstraintValidator : ConstraintValidator<ValidPasswordConstraint, String> {

    override fun isValid(password: String, context: ConstraintValidatorContext): Boolean {
        val validator = PasswordValidator(
            listOf(
                LengthRule(6, 30),
                CharacterRule(EnglishCharacterData.UpperCase, 1),
                CharacterRule(EnglishCharacterData.LowerCase, 1),
                CharacterRule(EnglishCharacterData.Digit, 1),
                CharacterRule(EnglishCharacterData.Special, 1),
                WhitespaceRule()
            )
        )
        val result: RuleResult = validator.validate(PasswordData(password))
        if (result.isValid) {
            return true
        }
        val messages: List<String> = validator.getMessages(result)

        val messageTemplate = messages.stream()
            .collect(Collectors.joining(";"))
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation()
            .disableDefaultConstraintViolation()
        return false
    }
}