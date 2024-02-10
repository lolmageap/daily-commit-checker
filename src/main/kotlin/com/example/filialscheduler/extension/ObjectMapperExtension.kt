package com.example.filialscheduler.extension

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters

val ObjectMapper.defaultSerializationMessage: BodyInserter<String, ReactiveHttpOutputMessage>
    get() = BodyInserters.fromValue(
        writeValueAsString("Message 발송")
    )

fun ObjectMapper.serializeMessage(message: String) =
    BodyInserters.fromValue(
        writeValueAsString(message)
    )
