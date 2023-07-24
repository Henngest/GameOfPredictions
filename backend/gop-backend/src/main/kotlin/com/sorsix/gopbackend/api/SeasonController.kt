package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Season
import com.sorsix.gopbackend.model.exceptions.CompetitionDoesNotExistException
import com.sorsix.gopbackend.repository.SeasonRepository
import com.sorsix.gopbackend.service.SeasonService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/competition/{competitionId}/seasons")
class SeasonController(
    val seasonService: SeasonService
) {

    @GetMapping
    fun getAllSeasons(@PathVariable competitionId: Long): List<Season> =
        this.seasonService.getAllByCompetition(competitionId)

    @GetMapping("/{id}")
    fun getSeason(@PathVariable competitionId: Long, @PathVariable id: Long): ResponseEntity<*> =
        this.seasonService.getByIdAndCompetition(id, competitionId)
            .let { ResponseEntity.ok(it) }
}