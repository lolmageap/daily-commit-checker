package com.example.filialscheduler.api

import com.example.filialscheduler.client.*
import com.example.filialscheduler.extension.defaultSerializeSuccessCommitMessage
import com.example.filialscheduler.extension.defaultSerializedFailureMessage
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
    private val blogClient: BlogClient,
    private val smsClient: SmsClient,
    private val objectMapper: ObjectMapper,
    private val emailClient: EmailClient,
) {
    @GetMapping("/github")
    suspend fun github() = githubClient.getCommitsCountForYesterday()

    @GetMapping("/blog")
    suspend fun blog() = blogClient.getLastPostId()

    @PostMapping("/slack/error")
    suspend fun slackFailureTest() = slackClient.sendMessage(
        objectMapper.defaultSerializedFailureMessage
    )

    @PostMapping("/slack/success")
    suspend fun slackSuccessTest() = slackClient.sendMessage(
        objectMapper.defaultSerializeSuccessCommitMessage
    )

    @PostMapping("/sms")
    suspend fun smsTest() = smsClient.sendSms()

    @PostMapping("/email")
    suspend fun emailTest() = emailClient.sendEmailToRyu()
}