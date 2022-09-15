package com.mobifyall.githubapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.ApiConstants
import com.mobifyall.githubapi.repos.GitHubRepo
import com.mobifyall.githubapi.viewstates.SearchBarUIState
import com.mobifyall.githubapi.viewstates.RepoUIState
import com.mobifyall.githubapi.viewstates.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: GitHubRepo,
) : ViewModel() {
    //region private properties
    private val viewModelState = MutableStateFlow(
        ViewModelState()
    )
    private var job: Job? = null
    //endregion

    //region public properties
    val uiState = viewModelState.map {
        it.toUIState()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUIState())

    val searchBarUIState = viewModelState.map {
        toSearchUIState(it)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, toSearchUIState(viewModelState.value))

    private fun toSearchUIState(it: ViewModelState): SearchBarUIState {
        return SearchBarUIState(it.searchTerm.orEmpty())
    }

    //endregion

    //region public behavior
    fun searchOrganization() {
        val term = viewModelState.value.searchTerm.orEmpty()
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.searchReposForOrganization(createQueryMap(term))
            viewModelState.update {
                it.copy(searchTerm = term, response = data, error = null, api = false)
            }
        }
    }

    fun inputSearchTerm(input: String) {
        viewModelState.update {
            it.copy(searchTerm = input)
        }
    }
    //endregion

    override fun onCleared() {
        super.onCleared()
        if (job?.isActive == true) {
            job?.cancel()
        }
    }
}

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


fun createQueryMap(orgName: String) = mutableMapOf<String, String>().apply {
    put(ApiConstants.KEY_ORDER, ApiConstants.VALUE_DESC)
    put(ApiConstants.KEY_Q, orgName)
    put(ApiConstants.KEY_S, ApiConstants.VALUE_STARS)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_REPOSITORIES)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_ORG)
    put(ApiConstants.KEY_PER_PAGE, ApiConstants.VALUE_DEFAULT_PER_PAGE)
}