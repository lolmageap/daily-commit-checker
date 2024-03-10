package com.example.filialscheduler.client

import com.example.filialscheduler.property.CherhyProperty
import kotlinx.coroutines.coroutineScope
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties(CherhyProperty::class)
class SmsClient(
    private val cherhyProperty: CherhyProperty,
) {
    private val messageService: DefaultMessageService =
        DefaultMessageService(
            cherhyProperty.coolsmsApiKey,
            cherhyProperty.coolsmsApiSecret,
            "https://api.coolsms.co.kr",
        )

    suspend fun sendSms() = coroutineScope {
        val message = Message(
            from = cherhyProperty.coolsmsFrom,
            to = cherhyProperty.randomNumber,
            text = """
                내가 내일 저녁에 밥살게
            """.trimIndent(),
        )

        messageService.sendOne(
            SingleMessageSendingRequest(message)
        )
    }
}