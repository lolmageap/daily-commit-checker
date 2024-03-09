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
    val githubName: String = github.user
    val githubToken: String = github.token

    val coolsmsApiKey: String = coolsms.apiKey
    val coolsmsApiSecret: String = coolsms.apiSecret
    val coolsmsFrom: String = coolsms.from

    val blogUser: String = blog.user
    val blogUrl: String = blog.url
    val blogToken: String = blog.token

    val randomNumber: String
        get() = when (Random.nextInt(3)) {
            0 -> phone.dad
            1 -> phone.mom
            else -> phone.girlfriend
        }
}