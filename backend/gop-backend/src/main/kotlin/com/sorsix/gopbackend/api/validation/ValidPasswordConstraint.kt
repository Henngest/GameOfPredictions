package com.sorsix.gopbackend.api.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.Documented
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass


@Documented
@Constraint(validatedBy = [PasswordConstraintValidator::class])
@Target(FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPasswordConstraint(
    val message: String = "Invalid Password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)