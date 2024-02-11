package com.example.filialscheduler

import com.example.filialscheduler.client.GithubClient
import com.example.filialscheduler.client.SlackClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
) {

    @GetMapping("/test")
    suspend fun test() = githubClient.getCommitsCountForYesterday()

    @PostMapping("/slack")
    suspend fun slackTest() = slackClient.sendMessage()

    @PostMapping("/sms")
    suspend fun smsTest() = slackClient.sendMessage()

}