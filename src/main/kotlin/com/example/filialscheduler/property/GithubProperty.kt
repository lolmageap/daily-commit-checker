package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "github")
class GithubProperty {
    lateinit var user: String
    lateinit var token: String
}