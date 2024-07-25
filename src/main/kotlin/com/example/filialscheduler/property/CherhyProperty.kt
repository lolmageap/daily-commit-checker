package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.random.Random

@ConfigurationProperties(prefix = "cherhy")
data class CherhyProperty(
    val coolsms: CoolsmsProperty,
    val phone: PhoneProperty,
    val github: GithubProperty,
    val blog: BlogProperty,
) {
    val githubName = github.user
    val githubToken = github.token

    val coolsmsApiKey = coolsms.apiKey
    val coolsmsApiSecret = coolsms.apiSecret
    val coolsmsFrom = coolsms.from

    val blogUser = blog.user

    val randomNumber
        get() = when (
            Random.nextInt(phone.totalContract)
        ) {
            0 -> phone.dad
            1 -> phone.mom
            else -> throw IllegalArgumentException("Invalid phone number")
        }
}