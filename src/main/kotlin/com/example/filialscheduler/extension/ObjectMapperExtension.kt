package com.example.filialscheduler.extension

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters

val ObjectMapper.defaultSerializedFailureMessage: BodyInserter<String, ReactiveHttpOutputMessage>
    get() = BodyInserters.fromValue(
        writeValueAsString(
            mapOf("text" to "문자 발송에 실패 했습니다.")
        )
    )

val ObjectMapper.defaultSerializeSuccessCommitMessage: BodyInserter<String, ReactiveHttpOutputMessage>
    get() = BodyInserters.fromValue(
        writeValueAsString(
            mapOf("text" to "오늘 하루도 커밋 하느라 고생 했습니다.^^")
        )
    )

fun ObjectMapper.serializeMessage(
    message: String,
) =
    BodyInserters.fromValue(
        writeValueAsString(
            mapOf("text" to message)
        )
    )