package com.rprandt.autorepairshop.costumer

data class CostumerRequest(
    val name: String,
    val email: String
) {
    fun toCostumer() = Costumer(
        name = name,
        email = email
    )
}
