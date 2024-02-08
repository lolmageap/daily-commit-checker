package com.example.filialscheduler

import kotlinx.coroutines.coroutineScope
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

suspend fun main() = coroutineScope {
    val githubClient = GithubClient()
    githubClient.getRepositories()
}

class GithubClient {

    suspend fun getRepositories() {
        val webClient = WebClient.create("https://api.github.com")
        val response = webClient.get()
            .uri("/users/lolmageap/events")
            .retrieve()
            .awaitBody<String>()

        println("response: $response")
    }

}