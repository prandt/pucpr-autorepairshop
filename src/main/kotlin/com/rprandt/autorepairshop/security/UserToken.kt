package com.rprandt.autorepairshop.security

import com.rprandt.autorepairshop.costumer.Costumer

data class UserToken(
    val id: Long,
    val name: String,
    val email: String
) {
    constructor(): this(0, "", "")
    constructor(costumer: Costumer): this(
        id = costumer.id!!,
        name = costumer.name!!,
        email = costumer.email!!
    )
}
