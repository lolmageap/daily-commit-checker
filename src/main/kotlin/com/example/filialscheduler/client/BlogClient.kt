package com.example.filialscheduler.client

import com.example.filialscheduler.property.CherhyProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
@EnableConfigurationProperties(CherhyProperty::class)
class BlogClient(
    private val cherhyProperty: CherhyProperty,
) {
    private val webClient = WebClient.create("https://velog.io")

    fun getPostsCountForLastWeek() {
        val html = fetchServerRenderedBlog()
            ?: throw RuntimeException("Failed to fetch server rendered blog")

        getLatestIdFromInitialData(html)
    }

    private fun getLatestIdFromInitialData(html: String): String {
        val initialData = html.split("initialData").last()
        println(initialData)
        return ""
    }

    private fun fetchServerRenderedBlog(): String? {
        val path = "/${cherhyProperty.blogUser}/posts"
        return webClient.get()
            .uri(path)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
    }
}