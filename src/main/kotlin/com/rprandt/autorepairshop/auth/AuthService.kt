package com.rprandt.autorepairshop.auth

import com.rprandt.autorepairshop.costumer.CostumerRepository
import com.rprandt.autorepairshop.costumer.CostumerResponse
import com.rprandt.autorepairshop.security.Jwt
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val costumerRepository: CostumerRepository,
    private val jwt: Jwt,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    fun login(request: LoginRequest): LoginResponse? {
        if (request.email == null) {
            log.warn("Email is required")
            return null
        }
        val costumer = costumerRepository.findByEmail(request.email)
        if (costumer == null) {
            log.warn("Costumer {} not found", request.email)
            return null
        }
        if (!passwordEncoder.matches(request.password, costumer.password)) {
            log.warn("Costumer passwords do not match")
            return null
        }
        log.info("User {} logged in", request.email)
        return LoginResponse(
            token = jwt.createToken(costumer),
            costumer = CostumerResponse(costumer)
        )
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(AuthService::class.java)
    }
}