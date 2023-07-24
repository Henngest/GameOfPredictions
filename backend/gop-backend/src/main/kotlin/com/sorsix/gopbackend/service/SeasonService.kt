package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Season

interface SeasonService {

    fun getAllByCompetition(competitionId: Long): MutableList<Season>

    fun getByIdAndCompetition(seasonId: Long, competitionId: Long): Season
}