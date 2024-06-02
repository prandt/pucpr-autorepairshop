package com.rprandt.autorepairshop.vehicle

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Vehicle (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var type: VehicleType? = VehicleType.CAR,
    var numberPlate: String? = null,
    var manufactureDate: String? = null
)