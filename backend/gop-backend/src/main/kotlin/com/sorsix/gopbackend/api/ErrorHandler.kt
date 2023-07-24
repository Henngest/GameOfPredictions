package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.exceptions.CompetitionDoesNotExistException
import com.sorsix.gopbackend.model.exceptions.SeasonDoesNotExistException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseBody
@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(CompetitionDoesNotExistException::class, SeasonDoesNotExistException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onCompetitionOrSeasonInvalidId(exception: RuntimeException): Map<String, String> =
        mapOf("error" to (exception.message ?: ""))
}