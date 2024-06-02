package com.rprandt.autorepairshop.vehicle

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicle")
class VehicleController (
    val vehicleService: VehicleService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<VehicleResponse>> =
        vehicleService.getAll()
            .map { VehicleResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Vehicle> =
        vehicleService.getById(id)
            ?.let { ResponseEntity.ok(it) }
            ?:ResponseEntity.notFound().build()
}