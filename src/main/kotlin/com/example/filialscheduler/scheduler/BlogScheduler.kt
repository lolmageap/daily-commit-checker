package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.BlogClient
import com.example.filialscheduler.client.SlackClient
import com.example.filialscheduler.client.SmsClient
import com.example.filialscheduler.component.FileComponent
import com.example.filialscheduler.constant.ASIA_SEOUL
import com.example.filialscheduler.constant.FIRST_DAY_ONE_MINUTE_PAST_MIDNIGHT
import com.example.filialscheduler.extension.defaultSerializedFailureMessage
import com.example.filialscheduler.extension.serializeMessage
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BlogScheduler(
    private val blogClient: BlogClient,
    private val fileComponent: FileComponent,
    private val slackClient: SlackClient,
    private val smsClient: SmsClient,
    private val objectMapper: ObjectMapper,
) {
    @Scheduled(cron = FIRST_DAY_ONE_MINUTE_PAST_MIDNIGHT, zone = ASIA_SEOUL)
    suspend fun blog() = coroutineScope {
        val thisMonthBlogId = async { blogClient.getLastPostId() }.await()
        val lastMonthBlogId = async { fileComponent.readLastBlogId() }.await()

        if (thisMonthBlogId != lastMonthBlogId) {
            launch {
                fileComponent.writeLastBlogId(thisMonthBlogId)
            }

            launch {
                try {
                    smsClient.sendSms()
                } catch (e: Exception) {
                    slackClient.sendMessage(
                        objectMapper.defaultSerializedFailureMessage,
                    )
                }
            }
        } else {
            slackClient.sendMessage(
                objectMapper.serializeMessage("블로그 글을 작성 하느라 고생 하셨습니다."),
            )
        }

        Unit
    }
}
