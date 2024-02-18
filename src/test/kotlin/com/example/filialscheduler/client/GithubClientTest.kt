package com.example.filialscheduler.client

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired

class GithubClientTest(
    @Autowired private val githubClient: GithubClient,
) : StringSpec({

    "전날의 커밋 개수를 확인 한다." {
        val count = githubClient.getCommitsCountForYesterday()
        count shouldNotBe null
    }

    "git 에 존재 하는 repository 를 정상적 으로 가져 오는지 확인 한다." {
        val count = githubClient.getRepositoryNames()
        count.size shouldBeGreaterThan 0
    }

    "git 에서 repository 의 이름 으로 commit 에 대한 정보를 가져 온다." {
        val commits = githubClient.getCommits("daily-commit-checker")
        commits.size shouldBeGreaterThan 0
    }

})
