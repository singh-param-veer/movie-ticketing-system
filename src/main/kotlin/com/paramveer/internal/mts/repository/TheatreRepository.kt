package com.paramveer.internal.mts.repository

import com.paramveer.internal.mts.Theatre
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TheatreRepository : CrudRepository<Theatre, UUID> {
}