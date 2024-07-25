package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ryu")
data class RyuProperty(
    val email: String,
)