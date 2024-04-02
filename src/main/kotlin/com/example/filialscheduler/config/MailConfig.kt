package com.example.filialscheduler.config

import com.example.filialscheduler.property.MailProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class MailConfig(
    private val mailProperty: MailProperty,
) {
    @Bean
    fun javaMailService(): JavaMailSender =
        mailProperty.toJavaMailSender()
}