package com.rprandt.autorepairshop.vehicle

import com.rprandt.autorepairshop.costumer.Costumer

data class VehicleRequest(
    val type: VehicleType,
    val numberPlate: String,
    val manufactureDate: String
) {
    fun toVehicle(costumer: Costumer): Vehicle {
        return Vehicle(
            costumer = costumer,
            type = type,
            manufactureDate = manufactureDate,
            numberPlate = numberPlate
        )
    }
}
