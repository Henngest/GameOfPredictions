package com.sorsix.gopbackend.model.partial

import java.time.LocalDateTime

class ParsedMatchday(
    var matchdayNumber: Long = 0,
    var startTime: LocalDateTime = LocalDateTime.MIN,
    var fixtures: Set<ParsedFixturePartial>? = HashSet()
)
