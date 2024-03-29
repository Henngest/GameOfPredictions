package com.sorsix.gopbackend.api.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraintValidator::class])
@Target(FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPasswordConstraint(
    val message: String = "Invalid Password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)