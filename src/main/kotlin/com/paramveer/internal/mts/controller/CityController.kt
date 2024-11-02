package com.paramveer.internal.mts.controller

import com.paramveer.internal.mts.City
import com.paramveer.internal.mts.repository.CityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/city")
class CityController(@Autowired private val cityRepository: CityRepository) {

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun createCity(@RequestBody city: City): ResponseEntity<*> {
        val created = cityRepository.save(city)
        return ResponseEntity.status(HttpStatus.CREATED).body(created.id)
    }

    @GetMapping
    fun getCities(): ResponseEntity<*> {
        return ResponseEntity.ok(cityRepository.findAll())
    }
}