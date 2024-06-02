package com.rprandt.autorepairshop.costumer

import com.rprandt.autorepairshop.vehicle.Vehicle
import com.rprandt.autorepairshop.vehicle.VehicleRequest
import com.rprandt.autorepairshop.vehicle.VehicleResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/costumer")
class CostumerController(
    private val costumerService: CostumerService
) {
    @PostMapping
    fun save(@RequestBody costumerRequest: CostumerRequest): ResponseEntity<CostumerResponse> =
        costumerRequest.toCostumer()
            .let {
                costumerService.save(it)
            }
            .let {
                ResponseEntity.status(HttpStatus.CREATED)
                    .body(CostumerResponse(it))
            }

    @GetMapping("/{costumerId}")
    fun findById(@PathVariable costumerId: Long): ResponseEntity<CostumerResponse> =
        costumerService.findById(costumerId)
            ?.let {
                ResponseEntity.ok().body(CostumerResponse(it))
            } ?: ResponseEntity.notFound().build()

    @PostMapping("/{costumerId}/vehicle")
    fun addVehicle(
        @RequestBody vehicleRequest: VehicleRequest,
        @PathVariable costumerId: Long
    ): ResponseEntity<Void> =
        costumerService.addVehicle(costumerId, vehicleRequest)
            ?.let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/{costumerId}/vehicles")
    fun getVehicles(@PathVariable costumerId: Long): ResponseEntity<List<VehicleResponse>> =
        costumerService.getCostumerVehicles(costumerId)
            .map { VehicleResponse(it) }
            .let { ResponseEntity.ok().body(it) }
}