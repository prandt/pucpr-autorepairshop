package com.rprandt.autorepairshop.vehicle

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository: JpaRepository<Vehicle, Long> {
    fun findVehicleByCostumerId(costumerId: Long): List<Vehicle>
}