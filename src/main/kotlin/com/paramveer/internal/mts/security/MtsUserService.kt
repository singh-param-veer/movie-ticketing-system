package com.paramveer.internal.mts.security

import com.paramveer.internal.mts.repository.AuthorityRepository
import com.paramveer.internal.mts.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class MtsUserService(
    private val passwordEncoder: PasswordEncoder, val usersRepository: UsersRepository,
    val authRepository: AuthorityRepository
) :
    UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        // Load user from database
        return usersRepository.findById(username!!).orElseThrow { throw Exception("User not found") }
    }

    fun createUser(username: String, password: String, role: String = "NORMAL"): User {
        // Create user in database


        val user = User()
        user.username2 = username
        user.password2 = passwordEncoder.encode(password)
        usersRepository.save(user)

        val authorities = MtsGrantedAuthority(username, role)
        authRepository.save(authorities)

        return user
    }
}
