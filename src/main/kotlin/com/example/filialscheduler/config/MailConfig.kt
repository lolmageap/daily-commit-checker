package com.example.filialscheduler.config

import com.example.filialscheduler.property.MailProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MailConfig(
    private val mailProperty: MailProperty,
) {
    @Bean
    fun javaMailService() =
        mailProperty.toJavaMailSender()
}