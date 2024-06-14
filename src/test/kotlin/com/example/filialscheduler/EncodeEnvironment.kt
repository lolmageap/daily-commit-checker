package com.example.filialscheduler

import io.kotest.core.spec.style.StringSpec
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EncodeEnvironment: StringSpec({
    "encrypt and decrypt text with jasypt" {
        val encryptKey = "your-encrypt-key"
        val encryptAlgorithm = "your-encrypt-algorithm"

        val listOfMap = listOf(
            mapOf("GITHUB_USER" to "your-github-username"),
            mapOf("GITHUB_TOKEN" to "your-github-token"),

            mapOf("COOLSMS_API_KEY" to "your-coolsms-api-key"),
            mapOf("COOLSMS_API_SECRET" to "your-coolsms-api-secret"),
            mapOf("COOLSMS_FROM" to "your-phone-number"),

            mapOf("MOM_PHONE" to "your-mom-phone-number"),
            mapOf("GIRLFRIEND_PHONE" to "your-girlfriend-phone-number"),
            mapOf("DAD_PHONE" to "your-dad-phone-number"),

            mapOf("SLACK_URL" to "your-slack-url"),

            mapOf("BLOG_USER" to "your-blog-username"),

            mapOf("LOCAL_BLOG_LOCATION" to "/home/ec2-user"),
            mapOf("LOCAL_BLOG_FILENAME" to "your-latest-blog.txt"),

            mapOf("ORIGIN_BLOG_LOCATION" to "/home/ec2-user"),

            mapOf("EMAIL_HOST" to "your-email-host"),
            mapOf("EMAIL_PORT" to "your-email-port"),
            mapOf("CHERHY_EMAIL" to "your-email"),
            mapOf("PASSWORD" to "your-email-password"),
        )

        val jasypt = StandardPBEStringEncryptor().apply {
            setPassword(encryptKey)
            setAlgorithm(encryptAlgorithm)
        }

        listOfMap.forEach {
            it.forEach { (key, value) ->
                val encrypt = jasypt.encrypt(value)
                println("$key=$encrypt")
            }
        }
    }
})