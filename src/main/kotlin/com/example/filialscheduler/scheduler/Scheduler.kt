package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.GithubClient
import com.example.filialscheduler.client.SlackClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
) {

    @Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
    suspend fun schedule() {
        val count = githubClient.getCommitsCountForYesterday()

        if (count == 0) {
            try {
                slackClient.sendMessage()
            } catch (e: Exception) {
                TODO("error handling")
            }
        }
    }
}