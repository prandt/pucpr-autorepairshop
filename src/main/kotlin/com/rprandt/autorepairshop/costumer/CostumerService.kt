package com.rprandt.autorepairshop.costumer

import com.rprandt.autorepairshop.security.Jwt
import com.rprandt.autorepairshop.vehicle.VehicleRepository
import com.rprandt.autorepairshop.vehicle.VehicleRequest
import com.rprandt.autorepairshop.vehicle.VehicleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CostumerService(
    private val costumerRepository: CostumerRepository,
    private val vehicleService: VehicleService,
    private val vehicleRepository: VehicleRepository,
    private val jwt: Jwt
) {

    fun save(costumer: Costumer) = costumerRepository.save(costumer)

    fun findById(costumerId: Long) = costumerRepository.findByIdOrNull(costumerId)

    fun addVehicle(costumerId: Long, request: VehicleRequest) =
       vehicleService.create(costumerId, request)
           ?.let {
               it.costumer.vehicles.add(it)
               it.costumer
           }
           ?.let {
               save(it)
           }

    fun getCostumerVehicles(costumerId: Long) =
        vehicleRepository.findVehicleByCostumerId(costumerId)

    fun delete(costumerId: Long) =
        costumerRepository.deleteById(costumerId)

    fun findAll(): MutableList<Costumer> = costumerRepository.findAll()

}