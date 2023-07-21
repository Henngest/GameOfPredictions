package com.sorsix.gopbackend.model

import com.sorsix.gopbackend.model.enumerations.Role
import javax.persistence.*

@Entity
@Table(name="users")
data class User(
    @Id
    val username: String,
    val password: String,
    val rating: Double,
    @Enumerated(value = EnumType.STRING)
    val role: Role,
    val country: String
)
