package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FixtureRepository : JpaRepository<Fixture, Long> {

    fun findAllByMatchday(matchday: Matchday): MutableList<Fixture>

    fun findByIdAndMatchday(fixtureId: Long, matchday: Matchday): Fixture?

    @Query("""
        SELECT f.* 
        FROM Fixture f
        WHERE f.matchday_id = ?1 AND f.home_team_id = ?2 AND f.away_team_id = ?3 
    """, nativeQuery = true)
    fun findByMatchdayIdAndHomeTeamAndAwayTeam(matchdayId: Long, homeTeam: String, awayTeam: String): Fixture?
}