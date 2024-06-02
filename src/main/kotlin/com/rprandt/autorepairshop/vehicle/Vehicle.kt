package com.rprandt.autorepairshop.vehicle

import com.fasterxml.jackson.annotation.JsonIgnore
import com.rprandt.autorepairshop.costumer.Costumer
import jakarta.persistence.*

@Entity
class Vehicle (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var type: VehicleType? = VehicleType.CAR,
    var numberPlate: String? = null,
    var manufactureDate: String? = null,
    @ManyToOne()
    @JoinColumn(name = "costumer_id")
    @JsonIgnore
    var costumer: Costumer
)