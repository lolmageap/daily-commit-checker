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
                안녕하세요. 어제 철희가 공부를 하지 않은 관계로 이렇게 문자를 발송 드립니다.
                공부를 하지 않은 날에는 이렇게 문자가 발송 됩니다.
                이 문자를 받으면 철희에게 밥을 얻어 먹으세요.
            """.trimIndent()
        )

        messageService.sendOne(
            SingleMessageSendingRequest(message)
        )
    }

}