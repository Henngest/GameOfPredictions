package com.sorsix.gopbackend.api.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.Documented
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [PasswordMatchConstraintValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordMatchConstraint(
    val message: String = "Passwords do not match.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
