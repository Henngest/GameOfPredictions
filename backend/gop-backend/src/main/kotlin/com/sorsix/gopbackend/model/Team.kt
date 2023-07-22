package com.sorsix.gopbackend.model

import jakarta.persistence.*

@Entity
data class Team(
    @Id
    val name: String,
    val logo: String
)
