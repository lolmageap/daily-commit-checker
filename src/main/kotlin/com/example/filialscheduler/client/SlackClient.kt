package com.example.filialscheduler.client

import com.example.filialscheduler.property.SlackProperty
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class SlackClient(
    private val slackProperty: SlackProperty,
) {
    private val webClient = WebClient.create(slackProperty.url)

    suspend fun sendMessage(
        serializedMessage: BodyInserter<String, ReactiveHttpOutputMessage>,
    ) = coroutineScope {
        launch {
            webClient.post()
                .body(serializedMessage)
                .retrieve()
                .awaitBody()
        }
        Unit
    }
}
