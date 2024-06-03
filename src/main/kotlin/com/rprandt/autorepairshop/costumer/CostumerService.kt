package com.rprandt.autorepairshop.costumer

import com.rprandt.autorepairshop.exception.BadRequestException
import com.rprandt.autorepairshop.exception.NotFoundException
import com.rprandt.autorepairshop.vehicle.VehicleRepository
import com.rprandt.autorepairshop.vehicle.VehicleRequest
import com.rprandt.autorepairshop.vehicle.VehicleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CostumerService(
    private val costumerRepository: CostumerRepository,
    private val vehicleService: VehicleService,
    private val vehicleRepository: VehicleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun save(costumer: Costumer) =
        try {
            costumer.password = passwordEncoder.encode(costumer.password)
            costumerRepository.save(costumer).also {
                log.info("Saved costumer: {}", it.email)
            }
        } catch (e: Throwable) {
            log.warn("Error while saving costumer", e)
            throw BadRequestException("Error while saving costumer")
        }

    fun findById(costumerId: Long) =
        costumerRepository.findByIdOrNull(costumerId) ?: throw NotFoundException("Not found costumer")

    fun addVehicle(costumerId: Long, request: VehicleRequest) =
       vehicleService.create(costumerId, request)
           .let {
               it.costumer.vehicles.add(it)
               it.costumer
           }
           .let {
               save(it)
               log.info("Added vehicle to costumer: {}", it)
           }

    fun getCostumerVehicles(costumerId: Long) =
        vehicleRepository.findVehicleByCostumerId(costumerId)

    fun delete(costumerId: Long) =
        try {
            costumerRepository.deleteById(costumerId)
            log.info("Deleted costumer: {}", costumerId)
        } catch (e: Throwable) {
            log.warn("Error to delete {}", costumerId)
            throw e
        }

    fun findAll(): MutableList<Costumer> = costumerRepository.findAll()

    fun update(costumer: Costumer) =
        costumer.let {
            if (it.id == null) throw BadRequestException("ID is required")
            costumerRepository.save(costumer)
        }

    companion object {
        val log: Logger = LoggerFactory.getLogger(CostumerService::class.java)
    }

}