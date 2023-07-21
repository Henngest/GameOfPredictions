package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Season
import com.sorsix.gopbackend.repository.SeasonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/seasons")
class SeasonController(
    val seasonRepository: SeasonRepository
) {

    @GetMapping
    fun getAllSeasons(): List<Season> = this.seasonRepository.findAll()

    @GetMapping("/{id}")
    fun getSeason(@PathVariable id: Long): ResponseEntity<*> = this.seasonRepository.findByIdOrNull(id)
    ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body("Season with id [$id] not found")
}