package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Prediction
import com.sorsix.gopbackend.model.dto.PredictionDto
import com.sorsix.gopbackend.service.PredictionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PredictionController(private val predictionService: PredictionService) {

    @PostMapping("/predict")
    fun makeAPrediction(@RequestBody predictions: List<PredictionDto>): ResponseEntity<*> =
        predictionService.createPredictionsForMatchday(predictions).let { ResponseEntity.ok(it) }

}