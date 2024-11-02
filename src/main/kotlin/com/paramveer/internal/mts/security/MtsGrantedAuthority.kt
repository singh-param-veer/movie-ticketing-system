package com.paramveer.internal.mts.security

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "authorities")
data class MtsGrantedAuthority(
    @Id @Column(name = "username") val username2: String? = null,
    @Column(name = "authority") var authority2: String? = null
) : GrantedAuthority {
    override fun getAuthority() = this.authority2!!
}
