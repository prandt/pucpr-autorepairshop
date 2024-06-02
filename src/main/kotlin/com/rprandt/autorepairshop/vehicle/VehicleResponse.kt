package com.rprandt.autorepairshop.vehicle

data class VehicleResponse(
    val type: VehicleType,
    val numberPlate: String,
    val manufactureDate: String
) {
    constructor(vehicle: Vehicle) : this(
        type = vehicle.type!!,
        numberPlate = vehicle.numberPlate!!,
        manufactureDate = vehicle.manufactureDate!!
    )
}
