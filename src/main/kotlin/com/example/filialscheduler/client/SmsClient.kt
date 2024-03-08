package com.example.filialscheduler.client

import com.example.filialscheduler.property.CoolsmsProperty
import com.example.filialscheduler.property.PhoneProperty
import com.example.filialscheduler.property.randomNumber
import kotlinx.coroutines.coroutineScope
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties(CoolsmsProperty::class, PhoneProperty::class)
class SmsClient(
    private val coolsmsProperty: CoolsmsProperty,
    private val phoneProperty: PhoneProperty,
) {

    private val messageService: DefaultMessageService =
        DefaultMessageService(coolsmsProperty.apiKey, coolsmsProperty.apiSecret, "https://api.coolsms.co.kr")

    suspend fun sendSms() = coroutineScope {
        val message = Message(
            from = coolsmsProperty.from,
            to = phoneProperty.randomNumber,
            text = """
                내가 내일 저녁에 밥살게
            """.trimIndent()
        )

        messageService.sendOne(
            SingleMessageSendingRequest(message)
        )
    }

}
