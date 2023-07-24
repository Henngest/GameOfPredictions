package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.Fixture

interface FixtureService {

    fun getAllByMatchday(matchdayId: Long): MutableList<Fixture>

    fun getByIdAndMatchday(fixtureId: Long, matchdayId: Long): Fixture
}