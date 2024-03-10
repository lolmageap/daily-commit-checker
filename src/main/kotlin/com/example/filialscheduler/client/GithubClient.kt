package com.example.filialscheduler.client

import com.example.filialscheduler.dto.GitHubCommit
import com.example.filialscheduler.dto.GithubRepositoryName
import com.example.filialscheduler.property.CherhyProperty
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
@EnableConfigurationProperties(CherhyProperty::class)
class GithubClient(
    private val cherhyProperty: CherhyProperty,
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

    suspend fun getRepositoryNames(
        page: Int = 1,
        size: Int = 50,
    ): List<GithubRepositoryName> = coroutineScope {
        val repositoriesUrl = "/users/${cherhyProperty.githubName}/repos?page=$page&per_page=$size"

        val response = webClient.get()
            .uri(repositoriesUrl)
            .header("Authorization", "token ${cherhyProperty.githubToken}")
            .retrieve()
            .awaitBody<MutableList<GithubRepositoryName>>()

        val hasNextPage = response.size == size
        if (hasNextPage) {
            val nextPageRepositories = getRepositoryNames(page + 1, size)
            response.addAll(nextPageRepositories)
        }

        response
    }

    suspend fun getCommits(
        repositoryName: String,
    ): List<GitHubCommit> = coroutineScope {
        val commitsUrl = "/repos/${cherhyProperty.githubName}/$repositoryName/commits"

        webClient.get()
            .uri(commitsUrl)
            .header("Authorization", "token ${cherhyProperty.githubToken}")
            .retrieve()
            .awaitBody<List<GitHubCommit>>()
    }
}