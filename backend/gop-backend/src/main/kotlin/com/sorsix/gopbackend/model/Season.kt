package com.sorsix.gopbackend.model

import jakarta.persistence.*

@Entity
data class Season(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @ManyToOne
    @JoinColumn(name="competition_id")
    val competition: Competition
)
