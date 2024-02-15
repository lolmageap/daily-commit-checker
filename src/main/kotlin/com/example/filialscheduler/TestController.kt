package com.example.filialscheduler

import com.example.filialscheduler.client.GithubClient
import com.example.filialscheduler.client.SlackClient
import com.example.filialscheduler.client.SmsClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
    private val smsClient: SmsClient,
) {

    @GetMapping("/github")
    suspend fun github() = githubClient.getCommitsCountForYesterday()

    @PostMapping("/slack")
    suspend fun slackTest() = slackClient.sendErrorMessage()

    @PostMapping("/sms")
    suspend fun smsTest() = smsClient.sendSms()

}