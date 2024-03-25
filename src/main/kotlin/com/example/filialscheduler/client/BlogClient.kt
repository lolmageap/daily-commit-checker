package com.example.filialscheduler.client

import com.example.filialscheduler.property.CherhyProperty
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
@EnableConfigurationProperties(CherhyProperty::class)
class BlogClient(
    private val cherhyProperty: CherhyProperty,
) {
    private val webClient = WebClient.create("https://velog.io")

    suspend fun getLastPostId() = coroutineScope {
        val html = fetchServerRenderedBlog()
        getLatestId(html)
    }

    private suspend fun fetchServerRenderedBlog() = coroutineScope {
        val path = "/${cherhyProperty.blogUser}/posts"
        webClient.get()
            .uri(path)
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()
            ?: throw RuntimeException("블로그 정보를 가져 오는데 실패 했습니다.")
    }

    private suspend fun getLatestId(html: String) = coroutineScope {
        async {
            val initialData = html.split("initialData").last()
            initialData.split("id\\\":\\\"")[1].substringBefore("\\")
        }.await()
    }
}