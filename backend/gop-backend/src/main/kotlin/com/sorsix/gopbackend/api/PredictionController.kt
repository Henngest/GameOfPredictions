package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Prediction
import com.sorsix.gopbackend.model.dto.PredictionDto
import com.sorsix.gopbackend.service.PredictionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PredictionController(private val predictionService: PredictionService) {

    @PostMapping("/predict")
    fun makeAPrediction(@RequestBody predictions: List<PredictionDto>): ResponseEntity<*> =
        predictionService.createPredictionsForMatchday(predictions).let { ResponseEntity.ok(it) }

    @GetMapping("/matchday/{id}/predictionsByUser/{userId}")
    fun getPredictionsForMatchdayMadeByUser(@PathVariable id: Long, @PathVariable userId: String) =
        predictionService.getAllPredictionsForMatchdayByUser(id, userId)
            .let { ResponseEntity.ok(it) }
}