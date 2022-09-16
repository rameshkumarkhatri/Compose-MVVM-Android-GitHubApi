package com.mobifyall.githubapi.repos

import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.GithubException

class GithubRepoFake(
    private var data: SearchResponse? = null,
    private var exception: GithubException? = null
) :
    GitHubRepo {

    fun setData(data: SearchResponse?, exception: GithubException?) {
        this.data = data
        this.exception = exception
    }

    override suspend fun searchReposForOrganization(map: Map<String, String>): SearchResponse {
        return data ?: throw exception ?: Exception()
    }
}