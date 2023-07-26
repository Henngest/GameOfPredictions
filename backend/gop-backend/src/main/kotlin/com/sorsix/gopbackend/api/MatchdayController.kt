package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.Matchday
import com.sorsix.gopbackend.service.MatchdayService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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

    // TODO Check if the uploaded file extension: .pdf, .csv, .txt etc.
    @PostMapping("/import")
    fun importMatchdaysFromFile(@PathVariable seasonId: Long, @RequestParam file: MultipartFile) {
        this.matchdayService.importMatchdaysFromFile(seasonId, file.inputStream)
    }
}