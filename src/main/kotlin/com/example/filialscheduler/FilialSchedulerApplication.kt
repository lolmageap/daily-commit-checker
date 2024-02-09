package com.example.filialscheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class FilialSchedulerApplication

fun main(args: Array<String>) {
    runApplication<FilialSchedulerApplication>(*args)
}
