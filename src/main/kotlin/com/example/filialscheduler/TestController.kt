package com.example.filialscheduler

import com.example.filialscheduler.client.GithubClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val githubClient: GithubClient,
) {

    @GetMapping("/test")
    suspend fun test() = githubClient.getCommitsCountForYesterday()

}