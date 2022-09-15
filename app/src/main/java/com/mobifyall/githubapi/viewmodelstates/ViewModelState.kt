package com.mobifyall.githubapi.viewmodelstates

import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.viewstates.RepoUIState
import com.mobifyall.githubapi.viewstates.SearchViewState

data class ViewModelState(
    val searchTerm: String? = null,
    val response: SearchResponse? = null,
    val error: String? = null,
    val api: Boolean = false
) {
    fun toUIState(): SearchViewState {
        return when {
            !api && error == null && response == null -> {
                SearchViewState.Nothing
            }
            error != null -> {
                SearchViewState.Error(error)
            }
            response != null -> {
                createSuccessUIState()
            }
            else -> {
                SearchViewState.Loading
            }
        }
    }

    private fun createSuccessUIState(): SearchViewState.Success {
        val list = response?.items?.map {
            RepoUIState(
                it.full_name.orEmpty(),
                it.stargazers_count.toString(),
                it.html_url.orEmpty(),
                it.description.orEmpty()
            )
        }?.toList() ?: emptyList()
        return SearchViewState.Success(title = "Search results for \'$searchTerm\'", list = list)
    }
}