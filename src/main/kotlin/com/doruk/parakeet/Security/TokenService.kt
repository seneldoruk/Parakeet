package com.doruk.parakeet.Security

import com.doruk.parakeet.ParakeetUser.ParakeetUser
import com.doruk.parakeet.ParakeetUser.ParakeetUserService
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * This service creates and parses tokens.
 * It will do database operations.
 */
@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: ParakeetUserService,
) {
    fun createToken(user: ParakeetUser): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("username", user.username)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): ParakeetUser? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["username"] as String
            userService.getUser(userId)
        } catch (e: Exception) {
            null
        }
    }
}