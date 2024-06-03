package com.rprandt.autorepairshop.auth

import com.rprandt.autorepairshop.costumer.CostumerResponse

data class LoginResponse(
    val token: String,
    val costumer: CostumerResponse
)