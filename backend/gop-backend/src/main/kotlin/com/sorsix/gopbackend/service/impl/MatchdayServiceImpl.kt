package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.exceptions.MatchdayDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.SeasonDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.TeamDoesNotExistException
import com.sorsix.gopbackend.repository.FixtureRepository
import com.sorsix.gopbackend.repository.MatchdayRepository
import com.sorsix.gopbackend.repository.SeasonRepository
import com.sorsix.gopbackend.repository.TeamRepository
import com.sorsix.gopbackend.service.MatchdayService
import com.sorsix.gopbackend.service.utils.FileParserUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class MatchdayServiceImpl(
    private val seasonRepository: SeasonRepository,
    private val matchdayRepository: MatchdayRepository,
    private val fileParserUtil: FileParserUtil,
    private val teamRepository: TeamRepository,
    private val fixtureRepository: FixtureRepository
) : MatchdayService {

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

    override fun importMatchdaysFromFile(seasonId: Long, file: InputStream) {
        val parsedMatchdays = this.fileParserUtil.parseMatchdaysFromFile(file)
        val season = this.seasonRepository.findByIdOrNull(seasonId)
            ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        parsedMatchdays.forEach { parsedMatchday ->
            val matchday = with(parsedMatchday) {
                Matchday(
                    id = 0,
                    matchdayNumber = matchdayNumber,
                    startTime = startTime,
                    season = season,
                    fixtures = null
                )
            }

            this.matchdayRepository.save(matchday)

            parsedMatchday.fixtures?.forEach { parsedFixture ->
                val homeTeam = this.teamRepository.findByIdOrNull(parsedFixture.homeTeam)
                    ?: throw TeamDoesNotExistException("Matchday with id [${parsedFixture.homeTeam}] does not exist.")
                val awayTeam = this.teamRepository.findByIdOrNull(parsedFixture.awayTeam)
                    ?: throw TeamDoesNotExistException("Matchday with id [${parsedFixture.awayTeam}] does not exist.")
                val fixture = with(parsedFixture) {
                    Fixture(
                        id = 0,
                        homeTeam = homeTeam,
                        awayTeam = awayTeam,
                        startTime = startTime,
                        homeTeamWinCoefficient = homeTeamWinCoefficient,
                        awayTeamWinCoefficient = awayTeamWinCoefficient,
                        drawCoefficient = drawCoefficient,
                        matchday = matchday,
                        homeTeamGoals = null,
                        awayTeamGoals = null,
                        outcome = null
                    )
                }

                this.fixtureRepository.save(fixture)
            }
        }
    }
}