package com.mobifyall.githubapi.viewstates

sealed class SearchViewState {
    object Loading : SearchViewState()
    class Error(val errorText: String) : SearchViewState()
    class Success(val title: String? = null, val list: List<RepoUIState>) : SearchViewState()
}