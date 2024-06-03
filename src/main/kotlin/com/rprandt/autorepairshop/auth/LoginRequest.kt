package com.rprandt.autorepairshop.auth

data class LoginRequest(
    val email: String?,
    val password: String?
)
