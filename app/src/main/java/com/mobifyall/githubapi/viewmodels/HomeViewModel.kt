package com.mobifyall.githubapi.viewmodels

import android.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobifyall.githubapi.core.models.SearchResponse
import com.mobifyall.githubapi.core.network.ApiConstants
import com.mobifyall.githubapi.repos.GitHubRepo
import com.mobifyall.githubapi.viewstates.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: GitHubRepo,
) : ViewModel() {
    //region private properties
    private val viewModelState = MutableStateFlow(
        ViewModelState()
    )
    //endregion

    //region public properties
    val uiState = viewModelState.map {
        it.toUIState()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUIState())
    //endregion

    //region public behavior
    //endregion


}

//data class ViewModelState(val searchTerm: String? = null, val response: SearchResponse? = null, val error: String? = null) {
//    fun toUIState(): SearchViewState {
//    return when {
//        error != null -> SearchViewState.Error(error)
//        response != null -> createSuccessUIState()
//
//    }
//    }
//
//    private fun createSuccessUIState() : SearchViewState.Success {
//
//    }
//}


fun createQueryMap(orgName: String) = mutableMapOf<String, String>().apply {
    put(ApiConstants.KEY_ORDER, ApiConstants.VALUE_DESC)
    put(ApiConstants.KEY_Q, orgName)
    put(ApiConstants.KEY_S, ApiConstants.VALUE_STARS)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_REPOSITORIES)
    put(ApiConstants.KEY_TYPE, ApiConstants.VALUE_ORG)
    put(ApiConstants.KEY_PER_PAGE, ApiConstants.VALUE_DEFAULT_PER_PAGE)
}