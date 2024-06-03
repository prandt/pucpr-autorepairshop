package com.rprandt.autorepairshop.vehicle

import com.rprandt.autorepairshop.costumer.CostumerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicle")
class VehicleController (
    val vehicleService: VehicleService
) {

    @GetMapping
    fun getAll(
        @RequestParam sortDir: String? = null
    ): ResponseEntity<List<VehicleResponse>> =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { vehicleService.getAll(it)}
            ?.map { VehicleResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Vehicle> =
        vehicleService.getById(id)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}/costumer")
    fun getCostumer(@PathVariable id: Long): ResponseEntity<CostumerResponse> =
        vehicleService.getCostumer(id)
            .let { ResponseEntity.ok().body(CostumerResponse(it)) }
}