package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "coolsms")
data class CoolsmsProperty(
    val apiKey: String,
    val apiSecret: String,
    val from: String,
)