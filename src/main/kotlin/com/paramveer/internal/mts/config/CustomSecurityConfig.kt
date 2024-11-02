package com.paramveer.internal.mts.config

import com.paramveer.internal.mts.filters.AuthTokenFilter
import com.paramveer.internal.mts.filters.JWTEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
class CustomSecurityConfig {

    @Bean
    fun passwordEncoder() = object : PasswordEncoder {
        override fun encode(rawPassword: CharSequence?): String = rawPassword.toString()
        override fun matches(rawPassword: CharSequence?, encodedPassword: String?) =
            Objects.equals(rawPassword, encodedPassword)
    }

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.requestMatchers("/login**", "/signup**").permitAll()
                .anyRequest().authenticated()
        }.exceptionHandling { it.authenticationEntryPoint(unauthorizedHandler) }
            .csrf { it.disable() }.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Autowired
    private lateinit var unauthorizedHandler: JWTEntryPoint

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter()
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(builder: AuthenticationConfiguration): AuthenticationManager {
        return builder.authenticationManager
    }

}
