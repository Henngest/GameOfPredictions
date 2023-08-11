package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.model.Season
import com.sorsix.gopbackend.model.Team
import com.sorsix.gopbackend.model.enumerations.Outcome
import com.sorsix.gopbackend.model.exceptions.FixtureDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.MatchdayDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.SeasonDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.TeamDoesNotExistException
import com.sorsix.gopbackend.model.partial.ParsedFixturePartial
import com.sorsix.gopbackend.model.partial.ParsedMatchday
import com.sorsix.gopbackend.repository.*
import com.sorsix.gopbackend.service.MatchdayService
import com.sorsix.gopbackend.service.PredictionService
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
    private val fixtureRepository: FixtureRepository,
    private val predictionRepository: PredictionRepository,
    private val predictionService: PredictionService
) : MatchdayService {

    override fun getAllBySeason(seasonId: Long): MutableList<Matchday> {
        val season = seasonRepository.findByIdOrNull(seasonId)
            ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        return matchdayRepository.findAllBySeasonOrderByMatchdayNumber(season)
    }

    override fun getById(matchdayId: Long): Matchday {
        return matchdayRepository.findByIdOrNull(matchdayId)
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

    private fun createMatchday(parsedMatchday: ParsedMatchday, season: Season): Matchday {
        return parsedMatchday.run {
            Matchday(
                id = 0,
                matchdayNumber = matchdayNumber,
                startTime = startTime,
                season = season,
                fixtures = null
            )
        }
    }

    private fun createFixture(parsedFixture: ParsedFixturePartial, matchday: Matchday, teams: HashMap<String, Team>): Fixture {
        return parsedFixture.run {
            val homeTeam = getTeam(teams, homeTeam)
            val awayTeam = getTeam(teams, awayTeam)

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
    }

    override fun importMatchdaysFromFile(seasonId: Long, file: InputStream) {
        val parsedMatchdays = this.fileParserUtil.parseMatchdaysFromFile(file)
        val season = this.seasonRepository.findByIdOrNull(seasonId)
            ?: throw SeasonDoesNotExistException("Season with id [$seasonId] does not exist.")

        val teams = HashMap<String, Team>()
        parsedMatchdays.forEach { parsedMatchday ->
            val matchday = createMatchday(parsedMatchday, season)
            this.matchdayRepository.save(matchday)

            parsedMatchday.fixtures?.forEach { parsedFixture ->
                val fixture = createFixture(parsedFixture, matchday, teams)
                this.fixtureRepository.save(fixture)
            }
        }
    }

    private fun getCoefficientFromOutcome(outcome: Outcome, fixture: Fixture): Double =
        when (outcome) {
            Outcome.HOME_TEAM_WIN -> fixture.homeTeamWinCoefficient
            Outcome.AWAY_TEAM_WIN -> fixture.awayTeamWinCoefficient
            else -> fixture.drawCoefficient
        }

    override fun importMatchdayResultsFromFile(matchdayId: Long, file: InputStream) {
        val fixtures = this.fileParserUtil.parseFixtureResultsFromFile(file)

        fixtures.forEach {
            val fixture =
                this.fixtureRepository.findByMatchdayIdAndHomeTeamAndAwayTeam(matchdayId, it.homeTeam, it.awayTeam)
                    ?: throw FixtureDoesNotExistException("Fixture with home team [${it.homeTeam}] and away team [${it.awayTeam} for matchday [${matchdayId}] does not exist")

            this.fixtureRepository.save(
                fixture.copy(
                    homeTeamGoals = it.homeTeamGoals,
                    awayTeamGoals = it.awayTeamGoals,
                    outcome = it.outcome
                )
            )
            this.predictionRepository.findAllByFixture(fixture).forEach { it2 ->
                val coefficient = getCoefficientFromOutcome(it2.predictedOutcome, fixture)
                this.predictionService.checkPredictionAndUpdateRating(
                    it2.predictedOutcome,
                    fixture.outcome!!,
                    it2.user,
                    coefficient
                )
            }
        }

        closeMatchdayForPredictions(matchdayId)
    }

    private fun closeMatchdayForPredictions(matchdayId: Long) {
        val matchday = this.matchdayRepository.findByIdOrNull(matchdayId)
            ?: throw MatchdayDoesNotExistException("Matchday with id [$matchdayId] does not exist.")

        this.matchdayRepository.save(
            matchday.copy(
                isFinished = true
            )
        )
    }
}