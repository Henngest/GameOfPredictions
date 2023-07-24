package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.Matchday
import org.springframework.data.jpa.repository.JpaRepository

interface FixtureRepository : JpaRepository<Fixture, Long> {

    fun findAllByMatchday(matchday: Matchday): MutableList<Fixture>

    fun findByIdAndMatchday(fixtureId: Long, matchday: Matchday): Fixture?
}