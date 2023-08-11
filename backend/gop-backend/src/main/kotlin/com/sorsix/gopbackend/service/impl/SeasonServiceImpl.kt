package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Season
import com.sorsix.gopbackend.model.exceptions.CompetitionDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.SeasonDoesNotExistException
import com.sorsix.gopbackend.repository.CompetitionRepository
import com.sorsix.gopbackend.repository.SeasonRepository
import com.sorsix.gopbackend.service.SeasonService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeasonServiceImpl(
    private val seasonRepository: SeasonRepository,
    private val competitionRepository: CompetitionRepository
) : SeasonService {

    override fun getAllByCompetition(competitionId: Long): MutableList<Season> {
        val competition = this.competitionRepository.findByIdOrNull(competitionId)
            ?: throw CompetitionDoesNotExistException("Competition with id [$competitionId] does not exist.")

        return this.seasonRepository.findAllByCompetition(competition)
    }

    override fun getById(seasonId: Long): Season =
        this.seasonRepository.findByIdOrNull(seasonId) ?: throw SeasonDoesNotExistException(
            "Season with id [$seasonId] does not exist."
        )
}