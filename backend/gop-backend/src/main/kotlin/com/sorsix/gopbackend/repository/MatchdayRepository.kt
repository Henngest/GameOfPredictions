package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Season
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MatchdayRepository : JpaRepository<Matchday, Long> {

    fun findAllBySeason(season: Season): MutableList<Matchday>

    @Query("SELECT m FROM Matchday m LEFT JOIN FETCH m.fixtures WHERE m.id = :matchdayId AND m.season = :season")
    fun findByIdAndSeason(matchdayId: Long, season: Season): Matchday?
}