package com.mobifyall.githubapi.viewstates

sealed class SearchViewState() {
    object Loading : SearchViewState()
    object Nothing : SearchViewState()
    class Error(val errorText: String) : SearchViewState()
    class Success(val title: String, val list: List<RepoUIState>,) : SearchViewState()
    //we can do the wildcard -     class Success<T>(val title: String? = null, val list: List<T>) : SearchViewState()
}