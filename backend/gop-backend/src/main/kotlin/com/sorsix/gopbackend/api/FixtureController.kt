package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Fixture
import com.sorsix.gopbackend.model.dto.FixtureEditDto
import com.sorsix.gopbackend.service.FixtureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PutMapping("/edit/{id}")
    fun editFixture(
        @PathVariable matchdayId: Long,
        @PathVariable id: Long,
        @RequestBody fixtureDto: FixtureEditDto
    ) {
        this.fixtureService.updateFixture(id, fixtureDto)
    }
}