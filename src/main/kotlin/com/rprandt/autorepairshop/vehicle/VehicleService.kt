package com.rprandt.autorepairshop.vehicle

import org.springframework.stereotype.Service

@Service
class VehicleService (val vehicleRepository: VehicleRepository) {

    fun save(vehicle: Vehicle) = vehicleRepository.save(vehicle)

    fun findAll() = vehicleRepository.findAll()

    fun findById(id: Long) = vehicleRepository.findById(id)
}