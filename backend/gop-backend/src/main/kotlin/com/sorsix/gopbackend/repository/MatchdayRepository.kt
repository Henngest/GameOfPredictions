package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Season
import org.springframework.data.jpa.repository.JpaRepository

interface MatchdayRepository : JpaRepository<Matchday, Long> {

    fun findAllBySeason(season: Season): MutableList<Matchday>

    fun findByIdAndSeason(matchdayId: Long, season: Season): Matchday?
}