package com.sorsix.gopbackend.model.dto

import java.time.LocalDateTime

data class FixtureEditDto(
    val homeTeamWinCoefficient: Double,
    val awayTeamWinCoefficient: Double,
    val drawCoefficient: Double,
    val startTime: LocalDateTime,
)