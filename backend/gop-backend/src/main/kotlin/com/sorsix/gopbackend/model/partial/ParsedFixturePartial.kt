package com.sorsix.gopbackend.model.partial

import com.fasterxml.jackson.annotation.JsonIgnore
import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Team
import com.sorsix.gopbackend.model.enumerations.Outcome
import jakarta.persistence.*
import java.time.LocalDateTime

class ParsedFixturePartial(
    var homeTeam: String,
    var awayTeam: String,
    var startTime: LocalDateTime,
    var homeTeamWinCoefficient: Double,
    var awayTeamWinCoefficient: Double,
    var drawCoefficient: Double
)