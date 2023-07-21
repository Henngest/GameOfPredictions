package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Competition
import com.sorsix.gopbackend.repository.CompetitionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/competitions")
class CompetitionController(val competitionRepository: CompetitionRepository) {

    @GetMapping
    fun getAllCompetitions(): MutableList<Competition> {
        return competitionRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getCompetitionById(@PathVariable id: Long) : ResponseEntity<*> = competitionRepository.findByIdOrNull(id)
    ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body("Competition with id [$id] not found")

}