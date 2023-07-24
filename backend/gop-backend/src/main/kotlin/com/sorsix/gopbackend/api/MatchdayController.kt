package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.service.MatchdayService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/seasons/{seasonId}/matchdays")
class MatchdayController(val matchdayService: MatchdayService) {

    @GetMapping
    fun getAllMatchdays(@PathVariable seasonId: Long): List<Matchday> =
            matchdayService.getAllBySeason(seasonId)

    @GetMapping("/{id}")
    fun getMatchdayById(@PathVariable seasonId: Long, @PathVariable id: Long): ResponseEntity<*> =
            matchdayService.getByIdAndSeason(id, seasonId)
                    .let { ResponseEntity.ok(it) }
}