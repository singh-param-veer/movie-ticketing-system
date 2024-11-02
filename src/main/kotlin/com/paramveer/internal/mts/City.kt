package com.paramveer.internal.mts

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import lombok.Data
import java.util.*

@Data
@Entity
@Table(name = "city")
data class City(
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: UUID? = null,
    @Column(name = "name") val name: String? = null,
    @Column(name = "state") val state: String? = null,
    @Column(name = "country") val country: String? = null,
    @JsonProperty("zipCode") @Column(name = "zipcode") val zipCode: String? = null
)
