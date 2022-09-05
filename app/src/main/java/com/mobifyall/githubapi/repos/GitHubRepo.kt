package com.mobifyall.githubapi.repos

import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.GitHubApiService

interface GitHubRepo {
    suspend fun searchReposForOrganization(map: Map<String, String>): SearchResponse
}

// we can implement cache here
class GitHubRepoImpl(private val service: GitHubApiService) : GitHubRepo {

    override suspend fun searchReposForOrganization(map: Map<String, String>): SearchResponse {
        return service.searchRepos(map)
    }
}