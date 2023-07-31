package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Prediction
import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.model.dto.PredictionDto
import com.sorsix.gopbackend.model.enumerations.Outcome

interface PredictionService {

    fun createPredictionsForMatchday(predictions: List<PredictionDto>): List<Prediction>

    fun checkPredictionAndUpdateRating(predictedOutcome: Outcome, fixtureOutcome: Outcome, userPredicting: User, coefficient: Double): Boolean
}