package com.paramveer.internal.mts.repository

import com.paramveer.internal.mts.security.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<User, String>
