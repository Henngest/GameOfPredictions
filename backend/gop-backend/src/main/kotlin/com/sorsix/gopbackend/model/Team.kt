package com.sorsix.gopbackend.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Team(
    @Id
    val name: String,
    val logo: String
)
