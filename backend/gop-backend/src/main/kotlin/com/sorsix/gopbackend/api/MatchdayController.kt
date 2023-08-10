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

    @PostMapping("/import")
    fun importMatchdaysFromFile(
        @PathVariable seasonId: Long,
        @RequestParam file: MultipartFile
    ): ResponseEntity<*> {
        return if (!isFileTypeSupported(file)) {
            ResponseEntity.badRequest().body(mapOf("error" to "Unsupported content type! Extensions must be .csv .txt or .docx"))
        } else {
            this.matchdayService.importMatchdaysFromFile(seasonId, file.inputStream)
            ResponseEntity.ok().body("")
        }
    }

    @PostMapping("/{id}/importResults")
    fun importMatchdayResultsFromFile(
        @PathVariable seasonId: Long,
        @PathVariable id: Long,
        @RequestParam file: MultipartFile
    ): ResponseEntity<*> {
        return if (!isFileTypeSupported(file)) {
            ResponseEntity.badRequest().body(mapOf("error" to "Unsupported content type! Extensions must be .csv .txt or .docx"))
        } else {
            this.matchdayService.importMatchdayResultsFromFile(id, file.inputStream)
            ResponseEntity.ok().body("")
        }
    }

    private fun isFileTypeSupported(file: MultipartFile): Boolean {
        val supportedMediaTypes = setOf(
            "text/plain",
            "text/csv",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        )

        return file.contentType in supportedMediaTypes
    }
}