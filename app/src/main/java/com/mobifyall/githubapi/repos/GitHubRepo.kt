package com.mobifyall.githubapi.repos

import com.mobifyall.githubapi.core.network.GitHubApiService

interface GitHubRepo {
    fun searchReposForOrganization(map: Map<String, String>)
}

class GitHubRepoImpl(service: GitHubApiService): GitHubRepo {

    override fun searchReposForOrganization(orgName: String) {
        TODO("Not yet implemented")
    }
}