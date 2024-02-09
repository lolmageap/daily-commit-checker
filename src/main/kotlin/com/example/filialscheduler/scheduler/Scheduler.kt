package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.GithubClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler(
    private val githubClient: GithubClient,
) {

    @Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
    suspend fun schedule() {
        githubClient.getCommitsCountForYesterday()
    }
}