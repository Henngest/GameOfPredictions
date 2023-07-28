package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Prediction
import org.springframework.data.jpa.repository.JpaRepository

interface PredictionRepository : JpaRepository<Prediction, Long> {
}