package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Team
import com.sorsix.gopbackend.model.exceptions.FixtureDoesNotExistException
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

    private fun getTeam(teams: HashMap<String, Team>, team: String): Team {
        return if (teams.containsKey(team)) {
            teams[team]!!
        } else {
            this.teamRepository.findByIdOrNull(team)
                ?.also { teams[team] = it }
                ?: throw TeamDoesNotExistException("Team with id [$team] does not exist.")
        }
    }

    override fun importMatchdaysFromFile(seasonId: Long, file: InputStream) {
        val parsedMatchdays = this.fileParserUtil.parseMatchdaysFromFile(file)
        val season = this.seasonRepository.findByIdOrNull(seasonId)
            ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        val teams = HashMap<String, Team>()
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
                val homeTeam = getTeam(teams, parsedFixture.homeTeam)
                val awayTeam = getTeam(teams, parsedFixture.awayTeam)
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

    override fun importMatchdayResultsFromFile(matchdayId: Long, file: InputStream) {
        val fixtures = this.fileParserUtil.parseFixtureResultsFromFile(file)

        fixtures.forEach {
            val fixture = this.fixtureRepository.findByMatchdayIdAndHomeTeamAndAwayTeam(matchdayId, it.homeTeam, it.awayTeam)
                ?: throw FixtureDoesNotExistException("Fixture with home team [${it.homeTeam}] and away team [${it.awayTeam} for matchday [${matchdayId}] does not exist")

            this.fixtureRepository.save(fixture.copy(
                homeTeamGoals = it.homeTeamGoals,
                awayTeamGoals = it.awayTeamGoals,
                outcome = it.outcome
            ))
        }
    }
}