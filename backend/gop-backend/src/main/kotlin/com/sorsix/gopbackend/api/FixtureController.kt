package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.service.FixtureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/matchdays/{matchdayId}/fixtures")
class FixtureController(val fixtureService: FixtureService) {

    @GetMapping
    fun getAllFixtures(@PathVariable matchdayId: Long): List<Fixture> =
            fixtureService.getAllByMatchday(matchdayId)

    @GetMapping("/{id}")
    fun getFixtureById(@PathVariable id: Long, @PathVariable matchdayId: Long): ResponseEntity<*> =
            fixtureService.getByIdAndMatchday(id, matchdayId)
                    .let { ResponseEntity.ok(it) }
}