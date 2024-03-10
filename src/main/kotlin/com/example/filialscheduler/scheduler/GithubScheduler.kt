package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.GithubClient
import com.example.filialscheduler.client.SlackClient
import com.example.filialscheduler.client.SmsClient
import com.example.filialscheduler.constant.ASIA_SEOUL
import com.example.filialscheduler.constant.EVERYDAY_ONE_MINUTE_PAST_TWELVE_PM
import com.example.filialscheduler.extension.defaultSerializeSuccessCommitMessage
import com.example.filialscheduler.extension.defaultSerializedFailureMessage
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.coroutineScope
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class GithubScheduler(
    private val githubClient: GithubClient,
    private val slackClient: SlackClient,
    private val smsClient: SmsClient,
    private val objectMapper: ObjectMapper,
) {
    @Scheduled(cron = EVERYDAY_ONE_MINUTE_PAST_TWELVE_PM, zone = ASIA_SEOUL)
    suspend fun githubCommit() = coroutineScope {
        val count = githubClient.getCommitsCountForYesterday()

        if (count == 0) {
            try {
                smsClient.sendSms()
            } catch (e: Exception) {
                slackClient.sendMessage(
                    objectMapper.defaultSerializedFailureMessage,
                )
            }
        } else {
            slackClient.sendMessage(
                objectMapper.defaultSerializeSuccessCommitMessage,
            )
        }
    }
}
