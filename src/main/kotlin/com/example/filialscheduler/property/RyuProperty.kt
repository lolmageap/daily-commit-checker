package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.random.Random

@ConfigurationProperties(prefix = "ryu")
data class RyuProperty(
    val email: String,
)