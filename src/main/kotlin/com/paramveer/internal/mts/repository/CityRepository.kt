package com.paramveer.internal.mts.repository

import com.paramveer.internal.mts.City
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CityRepository : CrudRepository<City, UUID> {
}