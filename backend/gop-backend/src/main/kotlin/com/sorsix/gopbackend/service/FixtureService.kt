package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.dto.FixtureEditDto

interface FixtureService {

    fun getAllByMatchday(matchdayId: Long): MutableList<Fixture>

    fun getByIdAndMatchday(fixtureId: Long, matchdayId: Long): Fixture

    fun updateFixture(fixtureId: Long, fixture: FixtureEditDto): Fixture
}