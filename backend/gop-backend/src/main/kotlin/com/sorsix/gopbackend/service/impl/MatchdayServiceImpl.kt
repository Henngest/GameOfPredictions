package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.exceptions.MatchdayDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.SeasonDoesNotExistException
import com.sorsix.gopbackend.repository.MatchdayRepository
import com.sorsix.gopbackend.repository.SeasonRepository
import com.sorsix.gopbackend.service.MatchdayService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MatchdayServiceImpl(private val seasonRepository: SeasonRepository,
                          private val matchdayRepository: MatchdayRepository) : MatchdayService {

    override fun getAllBySeason(seasonId: Long): MutableList<Matchday> {
        val season = seasonRepository.findByIdOrNull(seasonId)
                ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        return matchdayRepository.findAllBySeason(season)
    }

    override fun getByIdAndSeason(matchdayId: Long, seasonId: Long): Matchday {
        val season = seasonRepository.findByIdOrNull(seasonId)
                ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        return matchdayRepository.findByIdAndSeason(matchdayId, season)
                ?: throw MatchdayDoesNotExistException("Matchday with id [$matchdayId] does not exist.")
    }
}