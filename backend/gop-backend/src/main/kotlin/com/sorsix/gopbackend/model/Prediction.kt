package com.sorsix.gopbackend.model

import com.sorsix.gopbackend.model.enumerations.Outcome
import jakarta.persistence.*

@Entity
data class Prediction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Enumerated(value = EnumType.STRING)
    @Column(name="predicted_outcome")
    val predictedOutcome: Outcome,
    @ManyToOne
    @JoinColumn(name="user_id")
    val user: User,
    @ManyToOne
    @JoinColumn(name="fixture_id")
    val fixture: Fixture
)
