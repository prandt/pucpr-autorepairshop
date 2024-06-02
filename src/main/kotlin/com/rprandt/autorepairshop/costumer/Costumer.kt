package com.rprandt.autorepairshop.costumer

import com.fasterxml.jackson.annotation.JsonIgnore
import com.rprandt.autorepairshop.vehicle.Vehicle
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
class Costumer (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @NotNull
    var name: String? = "",
    @Column(unique = true, nullable = false)
    var email: String? = null,
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JsonIgnore
    val vehicles: MutableSet<Vehicle> = mutableSetOf()
)