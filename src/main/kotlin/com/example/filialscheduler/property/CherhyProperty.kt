package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.random.Random

@ConfigurationProperties(prefix = "cherhy")
data class CherhyProperty(
    val coolsmsProperty: CoolsmsProperty,
    val phoneProperty: PhoneProperty,
    val githubProperty: GithubProperty,
) {
    val githubName: String = githubProperty.user
    val githubToken: String = githubProperty.token

    val coolsmsApiKey: String = coolsmsProperty.apiKey
    val coolsmsApiSecret: String = coolsmsProperty.apiSecret
    val coolsmsFrom: String = coolsmsProperty.from

    val randomNumber: String
        get() = when (Random.nextInt(3)) {
            0 -> phoneProperty.dad
            1 -> phoneProperty.mom
            else -> phoneProperty.girlfriend
        }
}