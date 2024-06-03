package com.rprandt.autorepairshop.auth

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> =
        authService.login(loginRequest)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
}