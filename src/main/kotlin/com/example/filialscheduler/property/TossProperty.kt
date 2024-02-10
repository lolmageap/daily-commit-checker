package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "toss")
data class TossProperty(
    val clientKey: String,
    val secretKey: String,
)