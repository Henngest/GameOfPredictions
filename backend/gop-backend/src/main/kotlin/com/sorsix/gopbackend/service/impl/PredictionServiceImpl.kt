package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.Prediction
import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.model.dto.PredictionDto
import com.sorsix.gopbackend.model.enumerations.Outcome
import com.sorsix.gopbackend.model.exceptions.FixtureDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.UserDoesNotExistException
import com.sorsix.gopbackend.repository.FixtureRepository
import com.sorsix.gopbackend.repository.PredictionRepository
import com.sorsix.gopbackend.repository.UserRepository
import com.sorsix.gopbackend.service.PredictionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class PredictionServiceImpl(
    private val userRepository: UserRepository,
    private val predictionRepository: PredictionRepository,
    private val fixtureRepository: FixtureRepository
) : PredictionService {

    override fun createPredictionsForMatchday(predictions: List<PredictionDto>): List<Prediction> {
        val listOfPredictions: MutableList<Prediction> = mutableListOf()
        for (prediction in predictions) {
            val user = userRepository.findByUsername(prediction.userId)
                ?: throw UserDoesNotExistException("User with id [${prediction.userId}] does not exist.")
            val predictedOutcome = prediction.predictedOutcome
            val fixture = fixtureRepository.findByIdOrNull(prediction.fixtureId)
                ?: throw FixtureDoesNotExistException("Fixture with id [${prediction.fixtureId}] does not exist.")
            val predictionToSave = predictionRepository.findByUserAndFixture(prediction.userId, prediction.fixtureId)
                ?.copy(
                    predictedOutcome = prediction.predictedOutcome
                ) ?: Prediction(0, predictedOutcome, user, fixture)
            predictionRepository.save(predictionToSave)
            listOfPredictions.add(predictionToSave)
        }
        return listOfPredictions
    }

    override fun checkPredictionAndUpdateRating(
        predictedOutcome: Outcome,
        fixtureOutcome: Outcome,
        userPredicting: User,
        coefficient: Double
    ): Boolean {
        return if (predictedOutcome == fixtureOutcome) {
            userPredicting.rating += coefficient * (5 - 2 * abs(1 - coefficient))
            true
        } else {
            userPredicting.rating -= coefficient * (3 + 2 * abs(1 - coefficient))
            false
        }
    }

    override fun getAllPredictionsForMatchdayByUser(matchdayId: Long, userId: String): List<PredictionDto> {
        return this.predictionRepository.findAllByUserForMatchday(userId, matchdayId)
            .map {
                PredictionDto(
                    predictedOutcome = it.predictedOutcome,
                    userId = it.user.username,
                    fixtureId = it.fixture.id
                )
            }
    }
}