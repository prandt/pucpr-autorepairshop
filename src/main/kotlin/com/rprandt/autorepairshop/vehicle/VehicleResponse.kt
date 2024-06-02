package com.rprandt.autorepairshop.vehicle

data class VehicleResponse(
    val id: Long,
    val type: VehicleType,
    val numberPlate: String,
    val manufactureDate: String
) {
    constructor(vehicle: Vehicle) : this(
        id = vehicle.id!!,
        type = vehicle.type!!,
        numberPlate = vehicle.numberPlate!!,
        manufactureDate = vehicle.manufactureDate!!
    )
}
