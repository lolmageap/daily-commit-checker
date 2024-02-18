package com.example.filialscheduler.client

import com.example.filialscheduler.dto.GitHubCommit
import com.example.filialscheduler.dto.GithubRepositoryName
import com.example.filialscheduler.property.GithubProperty
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
@EnableConfigurationProperties(GithubProperty::class)
class GithubClient(
    private val githubProperty: GithubProperty,
) {
    private val webClient = WebClient.create("https://api.github.com")

    suspend fun getCommitsCountForYesterday(): Int = coroutineScope {
        val githubRepositoryNames = getRepositoryNames()

        val commits = githubRepositoryNames.map { repositoryName ->
            async {
                getCommits(repositoryName.name)
            }
        }.awaitAll().flatten()

        commits.count { it.yesterdayCommit }
    }

    suspend fun getRepositoryNames(): List<GithubRepositoryName> {
        val repositoriesUrl = "/users/${githubProperty.user}/repos?sort=created&direction=desc"

        return webClient.get()
            .uri(repositoriesUrl)
            .header("Authorization", "token ${githubProperty.token}")
            .retrieve()
            .awaitBody<List<GithubRepositoryName>>()
    }

    suspend fun getCommits(
        repositoryName: String,
    ): List<GitHubCommit> {
        val commitsUrl = "/repos/${githubProperty.user}/$repositoryName/commits"

        return webClient.get()
            .uri(commitsUrl)
            .header("Authorization", "token ${githubProperty.token}")
            .retrieve()
            .awaitBody<List<GitHubCommit>>()
    }
}