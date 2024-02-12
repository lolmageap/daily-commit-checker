package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.GithubClient
import com.example.filialscheduler.client.SlackClient
import com.example.filialscheduler.client.SmsClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
    private val smsClient: SmsClient,
) {

    @Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
    suspend fun schedule() {
        val count = githubClient.getCommitsCountForYesterday()

        if (count == 0) {
            try {
                smsClient.sendSms()
            } catch (e: Exception) {
                slackClient.sendMessage()
            }
        }

    }
}