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

    private fun updateParsedMatchdays(parsedMatchdays: MutableList<ParsedMatchday>, matchday: ParsedMatchday?, fixtures: Set<ParsedFixturePartial>?) {
        if (matchday != null) {
            matchday.fixtures = fixtures
            parsedMatchdays.add(matchday)
        }
    }

    private fun updateMatchdayWithNumberAndStartTime(iterator: Iterator<String>, line: String, matchday: ParsedMatchday) {
        val matchdayNumber = line.substringAfter("Matchday ").toLong()
        val startTime = LocalDateTime.parse(iterator.next())
        matchday.matchdayNumber = matchdayNumber
        matchday.startTime = startTime
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
                updateParsedMatchdays(parsedMatchdays, matchday, fixtures)

                matchday = ParsedMatchday()
                fixtures = HashSet()
                updateMatchdayWithNumberAndStartTime(iterator, line, matchday)
            } else {
                fixtures?.add(ParsedFixturePartial.createFromLine(line))
            }
        }

        updateParsedMatchdays(parsedMatchdays, matchday, fixtures)

        return parsedMatchdays
    }
}