package com.sorsix.gopbackend.model

import com.sorsix.gopbackend.model.enumerations.Role
import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    val username: String,
    val password: String,
    val rating: Double, //TODO() Make default value in database 0
    @Enumerated(value = EnumType.STRING)
    val role: Role,
    val country: String
)
