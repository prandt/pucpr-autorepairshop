package com.rprandt.autorepairshop.vehicle

import com.rprandt.autorepairshop.costumer.Costumer
import com.rprandt.autorepairshop.costumer.CostumerRepository
import com.rprandt.autorepairshop.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.data.domain.Sort

@Service
class VehicleService (
    val vehicleRepository: VehicleRepository,
    val costumerRepository: CostumerRepository
) {

    fun create(costumerId: Long, request: VehicleRequest) =
        costumerRepository.findByIdOrNull(costumerId)
            ?.let {
                vehicleRepository.save(request.toVehicle(it))
            } ?: throw NotFoundException("Costumer not found")
    
    fun getAll(dir: SortDir): MutableList<Vehicle> =
        when(dir) {
            SortDir.ASC -> vehicleRepository.findAll(Sort.by("type").ascending())
            SortDir.DESC -> vehicleRepository.findAll(Sort.by("type").descending())
        }

    fun getById(id: Long): Vehicle =
        vehicleRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Vehicle not found")

    fun getCostumer(id: Long): Costumer =
        vehicleRepository.findByIdOrNull(id)?.costumer
            ?: throw NotFoundException("Costumer not found")
}