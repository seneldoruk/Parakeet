package com.doruk.parakeet.security

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.stereotype.Component
import javax.crypto.spec.SecretKeySpec

/**
 * This class just sets the encoding settings for JWT.
 */
@Configuration
@Component
class JwtEncodingConfig {

    @Value("\${jwttoken}")
    private val jwtKey: String? = null

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val secretKey = SecretKeySpec(jwtKey!!.toByteArray(), "HmacSHA256")
        return NimbusJwtDecoder.withSecretKey(secretKey).build()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val secretKey = SecretKeySpec(jwtKey!!.toByteArray(), "HmacSHA256")
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}