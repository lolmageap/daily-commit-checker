package com.example.filialscheduler.client

import com.example.filialscheduler.property.MailProperty
import com.example.filialscheduler.property.RyuProperty
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import java.io.StringWriter
import kotlin.text.Charsets.UTF_8

@Component
class EmailClient(
    private val javaMailSender: JavaMailSender,
    private val mailProperty: MailProperty,
    private val ryuProperty: RyuProperty,
) {
    suspend fun sendEmailToRyu() = coroutineScope {
        val writer = StringWriter()
        val msg = writer.appendHTML().html {
            head {
                style {
                    +"""
                        .content {
                            font-size: 17px;
                            padding-right: 30px;
                            padding-left: 30px;
                        }
                    """.trimIndent()
                }
            }

            body {
                h1("title") {
                    style = "font-size: 30px; padding-right: 30px; padding-left: 30px;"
                    +"npm quill 확인해라"
                }
            }
        }.toString()

        val internetAddress = withContext(Dispatchers.IO) {
            InternetAddress(mailProperty.username, "Cherhy Jung")
        }
        val message = javaMailSender.createMimeMessage()
            .apply {
                addRecipients(MimeMessage.RecipientType.TO, ryuProperty.email)
                subject = "npm quill 확인해라"
                setText(msg, UTF_8.name(), "html")
                setFrom(internetAddress)
            }

        javaMailSender.send(message)
    }
}