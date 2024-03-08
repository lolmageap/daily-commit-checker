package com.example.filialscheduler

import io.kotest.core.spec.style.StringSpec
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EncodeEnvironment: StringSpec({
    "encrypt and decrypt text with jasypt" {

    }
})