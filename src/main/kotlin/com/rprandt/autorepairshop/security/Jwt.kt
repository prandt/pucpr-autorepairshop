package com.rprandt.autorepairshop.security

import com.rprandt.autorepairshop.costumer.Costumer
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

@Component
class Jwt {

    fun createToken(costumer: Costumer): String =
        UserToken(costumer).let {
            Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECURITY_TOKEN_COSTUMER.toByteArray()))
                .json(JacksonSerializer())
                .issuedAt(utcNow().toDate())
                .expiration(utcNow().plusSeconds(EXPIRE_TIME).toDate())
                .issuer(ISSUER)
                .subject(costumer.id.toString())
                .claim(COSTUMER_FIELD, it)
                .compact()
        }

    fun extractToken(req: HttpServletRequest): Authentication? {
        return try {
            val header = req.getHeader(AUTHORIZATION)
            if (header == null || !header.startsWith("Bearer")) return null
            val token = header.replace("Bearer ", "").trim()
            val claims = Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(SECURITY_TOKEN_COSTUMER.toByteArray()))
                .json(JacksonDeserializer(mapOf(COSTUMER_FIELD to UserToken::class.java)))
                .build()
                .parseSignedClaims(token)
                .payload

            if (claims.issuer != ISSUER) return null
            claims.get("costumer", UserToken::class.java).toAuthentication()
        } catch (e: Throwable) {
            log.debug("Exception occurred while extracting token", e)
            null
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(Jwt::class.java)
        const val SECURITY_TOKEN_COSTUMER = "1992bee7c6ad8286d674d778780479bd"
        const val EXPIRE_TIME = 3600L
        const val ISSUER = "AuthAutoRepair"
        const val COSTUMER_FIELD = "costumer"

        private fun utcNow() = ZonedDateTime.now(ZoneOffset.UTC)
        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())
        private fun UserToken.toAuthentication(): Authentication {
            val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_COSTUMER"))
            return UsernamePasswordAuthenticationToken.authenticated(this, id, authorities)
        }
    }

}