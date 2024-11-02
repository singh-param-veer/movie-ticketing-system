package com.paramveer.internal.mts.utils

import io.fusionauth.jwt.JWTEncoder
import io.fusionauth.jwt.domain.JWT
import io.fusionauth.jwt.hmac.HMACSigner
import io.fusionauth.jwt.hmac.HMACVerifier
import org.springframework.stereotype.Component
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class JWTUtils {

    fun generateToken(username: String): String {
        val jwt = JWT().setIssuer("mts").setSubject(username)
            .setExpiration(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).plusHours(1))
            .setIssuedAt(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")))
            .setNotBefore(ZonedDateTime.now())
            .setAudience("mts")
        return JWTEncoder().encode(jwt, HMACSigner.newSHA256Signer("secret"))
    }

    fun parseJwt(token: String): JWT {
        return JWT.getDecoder().decode(token, HMACVerifier.newVerifier("secret"))
    }
}