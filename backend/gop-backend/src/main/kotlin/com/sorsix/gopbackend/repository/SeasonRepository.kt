package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Competition
import com.sorsix.gopbackend.model.Season
import org.springframework.data.jpa.repository.JpaRepository

interface SeasonRepository : JpaRepository<Season, Long> {

    fun findAllByCompetition(competition: Competition): MutableList<Season>

    fun findByIdAndCompetition(seasonId: Long, competition: Competition): Season?

}