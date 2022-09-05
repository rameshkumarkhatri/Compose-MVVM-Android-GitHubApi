package com.mobifyall.githubapi.viewmodels

import android.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobifyall.githubapi.commons.getString
import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.ApiConstants
import com.mobifyall.githubapi.repos.GitHubRepo
import com.mobifyall.githubapi.viewstates.RepoUIState
import com.mobifyall.githubapi.viewstates.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

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
    //endregion

    //region public behavior
    fun searchOrganization(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.searchReposForOrganization(createQueryMap(name))
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
        return SearchViewState.Success("Search results for \'$searchTerm\'", list)
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