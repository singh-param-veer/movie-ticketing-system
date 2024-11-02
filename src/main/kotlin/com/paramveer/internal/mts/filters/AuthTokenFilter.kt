package com.paramveer.internal.mts.filters

import com.paramveer.internal.mts.utils.JWTUtils
import io.fusionauth.jwt.domain.JWT
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthTokenFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtUtils: JWTUtils

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("AuthTokenFilter doFilterInternal called for url: ${request.requestURI}")
        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader != null) {
                val token = parseJwt(authHeader)

                val user: UserDetails = userDetailsService.loadUserByUsername(token.subject)
                val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authToken
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e)
            throw RuntimeException(e)
        }
        filterChain.doFilter(request, response)
    }

    fun parseJwt(token: String): JWT {
        val tokenString = token.substring(7)
        return jwtUtils.parseJwt(tokenString)
    }
}