package com.mobifyall.githubapi.core.network

import com.mobifyall.githubapi.core.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun searchRepos(@QueryMap query: Map<String, String>): SearchResponse
}

object ApiConstants {
    const val KEY_ORDER = "o"
    const val KEY_Q = "q"
    const val KEY_S = "s" //for the sorting
    const val KEY_TYPE = "type"
    const val KEY_PER_PAGE = "per_page"
    const val VALUE_REPOSITORIES = "Repositories"
    const val VALUE_ORG = "org"
    const val VALUE_DESC = "desc"
    const val VALUE_STARS = "stars"
    const val VALUE_DEFAULT_PER_PAGE = "3"
}