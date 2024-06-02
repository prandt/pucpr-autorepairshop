package com.rprandt.autorepairshop.vehicle

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicle")
class VehicleController (
    val vehicleService: VehicleService
) {

    @GetMapping
    fun findAll() = vehicleService.findAll()

    @PostMapping
    fun create(@RequestBody vehicle: Vehicle) = vehicleService.save(vehicle)

}