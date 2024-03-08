package com.example.filialscheduler.config

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {
    @Bean
    fun jasyptStringEncryptor() =
        StandardPBEStringEncryptor().apply {
            setAlgorithm(
                System.getenv("JASYPT_ALGORITHM")
            )
            setPassword(
                System.getenv("JASYPT_PASSWORD")
            )
        }
}