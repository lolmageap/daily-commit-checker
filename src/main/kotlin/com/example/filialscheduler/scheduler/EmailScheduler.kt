package com.example.filialscheduler.scheduler

import com.example.filialscheduler.client.EmailClient
import com.example.filialscheduler.constant.ASIA_SEOUL
import com.example.filialscheduler.constant.EVERY_DAY_EIGHT_THIRTY_AM
import kotlinx.coroutines.coroutineScope
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class EmailScheduler(
    private val emailClient: EmailClient,
) {
    @Scheduled(cron = EVERY_DAY_EIGHT_THIRTY_AM, zone = ASIA_SEOUL)
    suspend fun sendEmailToRyu() = coroutineScope {
        emailClient.sendEmailToRyu()
    }
}
