package com.sorsix.gopbackend.model

import jakarta.persistence.*

@Entity
data class Competition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @Column(name="short_history")
    val shortHistory: String?
)