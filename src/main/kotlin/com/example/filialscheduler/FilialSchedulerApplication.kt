package com.example.filialscheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@ConfigurationPropertiesScan("com.example.filialscheduler.property")
@SpringBootApplication
class FilialSchedulerApplication

fun main(args: Array<String>) {
    runApplication<FilialSchedulerApplication>(*args)
}
