package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("local.location")
data class LocationProperty(
    val blog: String,
    val filename: String,
) {
    val blogPath: String
        get() = "$blog/$filename"
}
