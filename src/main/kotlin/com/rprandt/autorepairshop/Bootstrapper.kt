package com.rprandt.autorepairshop

import com.rprandt.autorepairshop.costumer.Costumer
import com.rprandt.autorepairshop.costumer.CostumerRepository
import com.rprandt.autorepairshop.vehicle.Vehicle
import com.rprandt.autorepairshop.vehicle.VehicleRepository
import com.rprandt.autorepairshop.vehicle.VehicleType
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    val costumerRepository: CostumerRepository,
    val vehicleRepository: VehicleRepository,
    val passwordEncoder: BCryptPasswordEncoder
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val costumers: List<Costumer> = listOf(
            Costumer(
                id = 1,
                name = "Jose da Silva",
                email = "jose@email.com",
                password = passwordEncoder.encode("jose123")
            ),
            Costumer(
                id = 2,
                name = "Richard Prandt",
                email = "richard@email.com",
                password = passwordEncoder.encode("richard123")
            ),
            Costumer(
                id = 3,
                name = "Eliza Podesta",
                email = "eliza@email.com",
                password = passwordEncoder.encode("eliza123")
            )
        )

        costumerRepository.saveAll(costumers)

        val vehicles: List<Vehicle> = listOf(
            Vehicle(
                numberPlate = "ABC-123",
                costumer = costumers[0],
                type = VehicleType.CAR,
                manufactureDate = "212121212"
            ).also { costumers[0].vehicles.add(it) },
            Vehicle(
                numberPlate = "MGP-9900",
                costumer = costumers[2],
                type = VehicleType.BIKE,
                manufactureDate = "121212"
            ).also { costumers[2].vehicles.add(it) }
        )

        vehicleRepository.saveAll(vehicles)
        costumerRepository.saveAll(costumers)
    }
}