package com.example.filialscheduler.client

import com.example.filialscheduler.dto.TossRequest
import com.example.filialscheduler.property.TossProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
@EnableConfigurationProperties(TossProperty::class)
class TossClient(
    private val tossProperty: TossProperty,
) {

    suspend fun remittance() {
        val tossRequest = TossRequest().apply {
            apiKey = tossProperty.apikey
        }
        val webClient = WebClient.create("https://pay.toss.im")

        webClient.post()
            .uri("/api/v2/payments")
            .body(
                BodyInserters.fromValue(tossRequest)
            )
    }

}