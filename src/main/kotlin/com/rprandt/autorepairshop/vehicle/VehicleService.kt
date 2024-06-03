package com.rprandt.autorepairshop.vehicle

import com.rprandt.autorepairshop.costumer.Costumer
import com.rprandt.autorepairshop.costumer.CostumerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VehicleService (
    val vehicleRepository: VehicleRepository,
    val costumerRepository: CostumerRepository
) {

    fun create(costumerId: Long, request: VehicleRequest) =
        costumerRepository.findByIdOrNull(costumerId)
            ?.let {
                vehicleRepository.save(request.toVehicle(it))
            }
    
    fun getAll(): MutableList<Vehicle> = vehicleRepository.findAll()

    fun getById(id: Long): Vehicle? = vehicleRepository.findByIdOrNull(id)

    fun getCostumer(id: Long): Costumer? =
        vehicleRepository.findByIdOrNull(id)?.costumer
}