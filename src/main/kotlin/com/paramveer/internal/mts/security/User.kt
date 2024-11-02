package com.paramveer.internal.mts.security

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class User : UserDetails {

    @Id
    @Column(name = "username")
    var username2: String = ""

    @Column(name = "password")
    var password2: String = ""

    @Column(name = "enabled")
    var enabled2: Boolean = false

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    var authorities2: List<MtsGrantedAuthority>? = null

    override fun getAuthorities(): List<GrantedAuthority> = authorities2!!.toList()

    override fun getPassword() = password2

    override fun getUsername() = username2

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = enabled2
}