package com.example.filialscheduler.client

import com.example.filialscheduler.constant.COOL_SMS_ENDPOINT
import com.example.filialscheduler.property.CherhyProperty
import kotlinx.coroutines.coroutineScope
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.stereotype.Component

@Component
class SmsClient(
    private val cherhyProperty: CherhyProperty,
) {
    private val messageService =
        DefaultMessageService(
            cherhyProperty.coolsmsApiKey,
            cherhyProperty.coolsmsApiSecret,
            COOL_SMS_ENDPOINT,
        )

    suspend fun sendSms() = coroutineScope {
        val message = Message(
            from = cherhyProperty.coolsmsFrom,
            to = cherhyProperty.randomNumber,
            text = "내일 저녁에 밥살게",
        )

        messageService.sendOne(
            SingleMessageSendingRequest(message)
        )
    }
}
