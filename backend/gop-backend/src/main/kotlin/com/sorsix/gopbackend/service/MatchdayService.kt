package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Season
import java.io.InputStream

interface MatchdayService {

    fun getAllBySeason(seasonId: Long): MutableList<Matchday>

    fun getByIdAndSeason(matchdayId: Long, seasonId: Long): Matchday

    fun importMatchdaysFromFile(seasonId: Long, file: InputStream)
}