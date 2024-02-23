package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.random.Random

@ConfigurationProperties(prefix = "phone")
data class PhoneProperty(
    val mom: String,
    val dad: String,
    val girlfriend: String,
) {
    val randomNumber: String
        get() = when (Random.nextInt(3)) {
            0 -> mom
            1 -> dad
            else -> girlfriend
        }
}