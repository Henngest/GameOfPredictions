package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.model.exceptions.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ResponseBody
@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(DoesNotExistException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onCompetitionOrSeasonInvalidId(exception: RuntimeException): Map<String, String> =
            mapOf("error" to (exception.message ?: ""))

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        println(ex.allErrors)
        return ResponseEntity.badRequest().body(mapOf("errors" to ex.allErrors.flatMap { it.defaultMessage!!.split(";") }))
    }
}