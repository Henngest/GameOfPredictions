package com.sorsix.gopbackend.service.utils

import com.sorsix.gopbackend.model.partial.ParsedFixturePartial
import com.sorsix.gopbackend.model.partial.ParsedMatchday
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime

@Component
class FileParserUtil {

    private fun getLinesFromInputStream(inputStream: InputStream): List<String> {
        return BufferedReader(InputStreamReader(inputStream))
            .use { it.readLines() }
    }

    fun parseMatchdaysFromFile(file: InputStream): List<ParsedMatchday> {
        val parsedMatchdays = mutableListOf<ParsedMatchday>()
        val lines = this.getLinesFromInputStream(file)

        val iterator = lines.iterator()
        var matchday: ParsedMatchday? = null
        var fixtures: MutableSet<ParsedFixturePartial>? = null
        while (iterator.hasNext()) {
            var line = iterator.next()
            if (line.startsWith("Matchday", true)) {
                if (matchday != null) {
                    matchday.fixtures = fixtures
                    parsedMatchdays.add(matchday)
                }

                matchday = ParsedMatchday()
                fixtures = HashSet()
                val matchdayNumber = line.substringAfter("Matchday ").toLong()
                line = iterator.next()
                val startTime = LocalDateTime.parse(line)
                matchday.matchdayNumber = matchdayNumber
                matchday.startTime = startTime
            } else {
                val parts = line.split(",")
                fixtures?.add(
                    ParsedFixturePartial(
                        homeTeam = parts[0].trim(),
                        awayTeam = parts[1].trim(),
                        startTime = LocalDateTime.parse(parts[2].trim()),
                        homeTeamWinCoefficient = parts[3].trim().toDouble(),
                        awayTeamWinCoefficient = parts[4].trim().toDouble(),
                        drawCoefficient = parts[5].trim().toDouble()
                    )
                )
            }
        }

        if (matchday != null) {
            matchday.fixtures = fixtures
            parsedMatchdays.add(matchday)
        }

        return parsedMatchdays
    }
}