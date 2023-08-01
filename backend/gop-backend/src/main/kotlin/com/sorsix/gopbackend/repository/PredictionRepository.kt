package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Prediction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PredictionRepository : JpaRepository<Prediction, Long> {

    fun findAllByFixture(fixture: Fixture): MutableList<Prediction>

    @Query(
        """
        SELECT p.* 
        FROM Prediction p
        WHERE p.user_id = ?1 AND p.fixture_id = ?2 
    """, nativeQuery = true
    )
    fun findByUserAndFixture(user: String, fixtureId: Long): Prediction?
}