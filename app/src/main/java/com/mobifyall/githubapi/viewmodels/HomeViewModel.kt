package com.mobifyall.githubapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobifyall.githubapi.core.network.ApiConstants
import com.mobifyall.githubapi.repos.GitHubRepo
import com.mobifyall.githubapi.viewmodelstates.ViewModelState
import com.mobifyall.githubapi.viewstates.SearchBarUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
        viewModelState.update {
            it.copy(error = exception.message, api = false)
        }
    }
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
        if (term.isNullOrBlank()) return // we can show the error or toast
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val data = repo.searchReposForOrganization(createQueryMap(term.addOrgAndColumn()))
            viewModelState.update {
                it.copy(searchTerm = term, response = data, error = null, api = true)
            }
        }
    }

    fun inputSearchTerm(input: String) {
        viewModelState.update {
            it.copy(searchTerm = input, api = false, response = null)
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


fun createQueryMap(orgName: String) = mutableMapOf<String, String>().apply {
    put(ApiConstants.KEY_ORDER, ApiConstants.VALUE_DESC)
    put(ApiConstants.KEY_Q, orgName)
    put(ApiConstants.KEY_S, ApiConstants.VALUE_STARS)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_REPOSITORIES)
    put(ApiConstants.KEY_PER_PAGE, ApiConstants.VALUE_DEFAULT_PER_PAGE)
}

fun String.addOrgAndColumn() = "${ApiConstants.VALUE_ORG}:$this"