package com.example.filialscheduler.client

import com.example.filialscheduler.property.CherhyProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties(CherhyProperty::class)
class BlogClient {
    fun getPostsCountForLastWeek(): Int {
        return 0
    }
}