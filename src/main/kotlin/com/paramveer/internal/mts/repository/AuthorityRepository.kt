package com.paramveer.internal.mts.repository

import com.paramveer.internal.mts.security.MtsGrantedAuthority
import com.paramveer.internal.mts.security.User
import org.springframework.data.repository.CrudRepository

interface AuthorityRepository : CrudRepository<MtsGrantedAuthority, String>
