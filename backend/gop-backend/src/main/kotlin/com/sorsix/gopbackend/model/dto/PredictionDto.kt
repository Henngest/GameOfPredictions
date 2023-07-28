package com.sorsix.gopbackend.model.dto

import com.sorsix.gopbackend.model.enumerations.Outcome

data class PredictionDto(
        val predictedOutcome: Outcome,
        val userId: String,
        val fixtureId: Long
)
