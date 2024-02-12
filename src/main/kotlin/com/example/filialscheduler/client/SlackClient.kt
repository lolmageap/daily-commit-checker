package com.example.filialscheduler.client

import com.example.filialscheduler.extension.defaultSerializeMessage
import com.example.filialscheduler.property.SlackProperty
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
@EnableConfigurationProperties(SlackProperty::class)
class SlackClient(
    private val slackProperty: SlackProperty,
    private val objectMapper: ObjectMapper,
) {

    suspend fun sendMessage(): Unit = coroutineScope {
        val webClient = WebClient.create(slackProperty.url)
        val message = objectMapper.defaultSerializeMessage

        try {
            launch {
                webClient.post()
                    .body(message)
                    .retrieve()
                    .awaitBody()
            }
        } catch (e: Exception) {
            TODO("Exception handling")
        }
    }

}