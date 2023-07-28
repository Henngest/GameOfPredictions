package com.sorsix.gopbackend.model.partial

import com.sorsix.gopbackend.model.enumerations.Outcome
import java.time.LocalDateTime

data class ParsedFixtureResultPartial(
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamGoals: Long,
    val awayTeamGoals: Long,
    val outcome: Outcome
) {
    companion object {
        fun createFromLine(line: String) : ParsedFixtureResultPartial {
            val parts = line.split(",")
            val homeTeamGoals = parts[2].toLong()
            val awayTeamGoals = parts[3].toLong()
            val outcome = when {
                homeTeamGoals > awayTeamGoals -> Outcome.HOME_TEAM_WIN
                awayTeamGoals > homeTeamGoals -> Outcome.AWAY_TEAM_WIN
                else -> Outcome.DRAW
            }
            return ParsedFixtureResultPartial(
                homeTeam = parts[0],
                awayTeam = parts[1],
                homeTeamGoals = homeTeamGoals,
                awayTeamGoals = awayTeamGoals,
                outcome = outcome
            )
        }
    }
}
