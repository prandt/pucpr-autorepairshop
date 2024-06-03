package com.rprandt.autorepairshop.exception

import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class StandardException (
    val timestamp: Long,
    val status: Int,
    val message: String?
)

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(e: BadRequestException) =
        StandardException(
            timestamp = System.currentTimeMillis(),
            status = BAD_REQUEST.value(),
            message = if (e.message != null) e.message else BAD_REQUEST.reasonPhrase,
        ).let {
            ResponseEntity.badRequest().body(it)
        }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException) =
        StandardException(
            timestamp = System.currentTimeMillis(),
            status = NOT_FOUND.value(),
            message = if (e.message != null) e.message else NOT_FOUND.reasonPhrase,
        ).let {
            ResponseEntity.status(NOT_FOUND).body(it)
        }

}