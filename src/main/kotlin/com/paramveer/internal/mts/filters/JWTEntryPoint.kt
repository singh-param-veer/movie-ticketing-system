package com.paramveer.internal.mts.filters

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component


@Component
class JWTEntryPoint : AuthenticationEntryPoint {

    private val logger = LoggerFactory.getLogger(JWTEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        logger.error("Unauthorized error: {}", authException?.message)

        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.status = HttpServletResponse.SC_UNAUTHORIZED

        val body: MutableMap<String, Any> = HashMap()
        body["status"] = HttpServletResponse.SC_UNAUTHORIZED
        body["error"] = "Unauthorized"
        body["message"] = authException?.message ?: "Unauthorized"
        body["path"] = request!!.requestURI

        val mapper = ObjectMapper()
        mapper.writeValue(response?.outputStream, body)
    }

}