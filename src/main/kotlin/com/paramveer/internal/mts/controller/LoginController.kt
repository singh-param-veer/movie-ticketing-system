package com.paramveer.internal.mts.controller

import com.paramveer.internal.mts.security.MtsUserService
import com.paramveer.internal.mts.utils.JWTUtils
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant


data class LoginRequest(val username: String, val password: String)

data class SignupRequest(val username: String, val email: String, val password: String, val role: String = "NORMAL")


@Slf4j
@RestController
class LoginController {

    private val logger = LoggerFactory.getLogger(LoginController::class.java)

    @Autowired
    private lateinit var jwtUtils: JWTUtils

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userService: MtsUserService

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        try {
            val user = userService.loadUserByUsername(loginRequest.username)
            return if (passwordEncoder.matches(loginRequest.password, user.password)) {

                ResponseEntity.ok(
                    mapOf(
                        "access_token" to jwtUtils.generateToken(loginRequest.username),
                        "created_at" to Instant.now(),
                        "expiry_in" to "1hour"
                    )
                )
            } else {
                ResponseEntity.badRequest().body("Invalid password")
            }
        } catch (e: Exception) {
            logger.info("exception raised while login: ${e.message}")
            return ResponseEntity.badRequest().body("Invalid username")
        }
    }

    @PostMapping("/signup")
    fun signup(@RequestBody signupRequest: SignupRequest): ResponseEntity<*> {
        // Add user to database
        userService.createUser(signupRequest.username, signupRequest.password, signupRequest.role)
        return ResponseEntity.ok(mapOf("message" to "User created successfully"))
    }
}

