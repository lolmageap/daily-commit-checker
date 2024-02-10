package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "notification.slack.webhook")
data class SlackProperty(
    val url: String,
)