package com.paramveer.internal.mts.controller

import com.paramveer.internal.mts.Theatre
import com.paramveer.internal.mts.repository.CityRepository
import com.paramveer.internal.mts.repository.TheatreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("theatre")
class TheatreController(
    @Autowired private val theatreRepository: TheatreRepository,
    @Autowired private val cityRepository: CityRepository
) {

    @PostMapping()
    fun createTheatre(@RequestBody theatre: Theatre, @RequestParam cityId: UUID): ResponseEntity<*> {
        val cityFromDB = cityRepository.findById(cityId)
        val created = theatreRepository.save(theatre.copy(city = cityFromDB.get()))
        return ResponseEntity.ok(created.id)
    }

    @GetMapping()
    fun allTheatres(): ResponseEntity<*> {
        return ResponseEntity.ok(theatreRepository.findAll())
    }
}