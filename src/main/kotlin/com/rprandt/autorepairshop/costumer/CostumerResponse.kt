package com.rprandt.autorepairshop.costumer

data class CostumerResponse(
    val id: Long,
    val name: String,
    val email: String,
) {
    constructor(costumer: Costumer) : this(
        id = costumer.id!!,
        name = costumer.name!!,
        email = costumer.email!!
    )
}
