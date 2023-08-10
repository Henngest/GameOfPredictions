package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.dto.FixtureEditDto
import com.sorsix.gopbackend.model.exceptions.FixtureDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.MatchdayDoesNotExistException
import com.sorsix.gopbackend.repository.FixtureRepository
import com.sorsix.gopbackend.repository.MatchdayRepository
import com.sorsix.gopbackend.service.FixtureService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FixtureServiceImpl(
    private val matchdayRepository: MatchdayRepository,
    private val fixtureRepository: FixtureRepository
) : FixtureService {

    override fun getAllByMatchday(matchdayId: Long): MutableList<Fixture> {
        val matchday = matchdayRepository.findByIdOrNull(matchdayId)
            ?: throw MatchdayDoesNotExistException("Matchday with id [$matchdayId] does not exist.")

        return fixtureRepository.findAllByMatchday(matchday)
    }

    override fun getByIdAndMatchday(fixtureId: Long, matchdayId: Long): Fixture {
        val matchday = matchdayRepository.findByIdOrNull(matchdayId)
            ?: throw MatchdayDoesNotExistException("Matchday with id [$matchdayId] does not exist.")

        return fixtureRepository.findByIdAndMatchday(fixtureId, matchday)
            ?: throw FixtureDoesNotExistException("Fixture with id [$fixtureId] does not exist.")
    }

    override fun updateFixture(fixtureId: Long, fixture: FixtureEditDto): Fixture {
        val f = this.fixtureRepository.findByIdOrNull(fixtureId)
            ?: throw FixtureDoesNotExistException("Fixture with id [$fixtureId] does not exist.")

        return this.fixtureRepository.save(
            f.copy(
                homeTeamWinCoefficient = fixture.homeTeamWinCoefficient,
                drawCoefficient = fixture.drawCoefficient,
                awayTeamWinCoefficient = fixture.awayTeamWinCoefficient,
                startTime = fixture.startTime
            )
        )
    }
}