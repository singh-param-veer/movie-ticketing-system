package com.paramveer.internal.mts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories("com.paramveer.internal.*")
@SpringBootApplication
class MtsApplication

fun main(args: Array<String>) {
    runApplication<MtsApplication>(*args)
}
