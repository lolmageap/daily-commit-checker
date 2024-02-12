package com.example.filialscheduler.extension

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters

val ObjectMapper.defaultSerializeMessage: BodyInserter<String, ReactiveHttpOutputMessage>
    get() = BodyInserters.fromValue(
        writeValueAsString(
            mapOf("text" to "Slack message")
        )
    )

fun ObjectMapper.serializeMessage(message: String) =
    BodyInserters.fromValue(
        writeValueAsString(
            mapOf("text" to message)
        )
    )
