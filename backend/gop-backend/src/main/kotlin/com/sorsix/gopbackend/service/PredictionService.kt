package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Prediction
import com.sorsix.gopbackend.model.dto.PredictionDto

interface PredictionService {

    fun createPredictionsForMatchday(predictions: List<PredictionDto>): List<Prediction>
}