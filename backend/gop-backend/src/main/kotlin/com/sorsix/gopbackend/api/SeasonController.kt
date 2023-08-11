package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Season
import com.sorsix.gopbackend.service.SeasonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/competition/{competitionId}/seasons")
class SeasonController(
    val seasonService: SeasonService
) {

    @GetMapping
    fun getAllSeasons(@PathVariable competitionId: Long): List<Season> =
        this.seasonService.getAllByCompetition(competitionId)

    @GetMapping("/{id}")
    fun getSeason(@PathVariable competitionId: Long, @PathVariable id: Long): ResponseEntity<Season> =
        this.seasonService.getById(id)
            .let { ResponseEntity.ok(it) }
}