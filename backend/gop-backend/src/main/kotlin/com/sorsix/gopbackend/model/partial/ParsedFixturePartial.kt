package com.sorsix.gopbackend.model.partial

import java.time.LocalDateTime

class ParsedFixturePartial(
        var homeTeam: String,
        var awayTeam: String,
        var startTime: LocalDateTime,
        var homeTeamWinCoefficient: Double,
        var awayTeamWinCoefficient: Double,
        var drawCoefficient: Double
) {

    companion object {
        fun createFromLine(line: String): ParsedFixturePartial {
            val parts = line.split(",")
            return ParsedFixturePartial(
                    homeTeam = parts[0].trim(),
                    awayTeam = parts[1].trim(),
                    startTime = LocalDateTime.parse(parts[2].trim()),
                    homeTeamWinCoefficient = parts[3].trim().toDouble(),
                    drawCoefficient = parts[4].trim().toDouble(),
                    awayTeamWinCoefficient = parts[5].trim().toDouble()
            )
        }
    }

}