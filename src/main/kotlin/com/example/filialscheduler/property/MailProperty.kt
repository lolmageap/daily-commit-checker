package com.example.filialscheduler.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*
import kotlin.text.Charsets.UTF_8

@ConfigurationProperties("spring.mail")
data class MailProperty(
    val username: String,
    val password: String,
    val host: String,
    val port: Int,
) {
    private val mailProperties =
        Properties().apply {
            setProperty("mail.transport.protocol", "smtp")
            setProperty("mail.smtp.auth", "true")
            setProperty("mail.smtp.starttls.enable", "true")
            setProperty("mail.debug", "true")
            setProperty("mail.smtp.ssl.trust", "smtp.mailplug.co.kr")
            setProperty("mail.smtp.ssl.enable", "true")
        }

    fun toJavaMailSender() =
        JavaMailSenderImpl().also {
            it.host = host
            it.username = username
            it.password = password
            it.port = port
            it.javaMailProperties = mailProperties
            it.defaultEncoding = UTF_8.name()
        }
}