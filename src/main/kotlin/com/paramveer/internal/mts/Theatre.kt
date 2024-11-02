package com.paramveer.internal.mts

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "theatre")
data class Theatre(
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: UUID? = null,
    val name: String? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cityid", referencedColumnName = "id") val city: City? = null
)