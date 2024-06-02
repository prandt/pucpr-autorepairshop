package com.rprandt.autorepairshop.costumer

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CostumerRepository: JpaRepository<Costumer, Long>{
}