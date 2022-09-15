package com.mobifyall.githubapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.ui.components.ErrorUIComponent
import com.mobifyall.githubapi.ui.components.HeaderUIComponent
import com.mobifyall.githubapi.ui.components.RowGitHubRepoUIComponent
import com.mobifyall.githubapi.ui.components.SearchInputComponent
import com.mobifyall.githubapi.viewmodels.HomeViewModel
import com.mobifyall.githubapi.viewstates.SearchViewState

@Composable
fun SearchScreen(
    viewModel: HomeViewModel,
    onNavigateBack: () -> Unit,
    showDescriptionClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchBarUIState by viewModel.searchBarUIState.collectAsState()
    Scaffold(Modifier,
        topBar = {
            TopAppBar(modifier = Modifier,
                title = {}, //no title
                actions = {
                    SearchInputComponent(
                        searchBarUIState,
                        updateInput = viewModel::inputSearchTerm,
                        doneClicked = viewModel::searchOrganization
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier,
                            contentDescription = stringResource(id = R.string.content_description_back_icon)
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(1f)
                .background(Color.White),
        ) {

            when (uiState) {
                is SearchViewState.Success -> {
                    val data = (uiState as SearchViewState.Success)
                    HeaderUIComponent(data.title)
                    LazyColumn(content = {
                        itemsIndexed(
                            data.list
                        ) { _, item ->
                            RowGitHubRepoUIComponent(item, showDescriptionClicked)
                            Divider(
                                Modifier.padding(16.dp, 0.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                        }
                    })
                }
                is SearchViewState.Error -> {
                    ErrorUIComponent((uiState as SearchViewState.Error).errorText)
                }
                is SearchViewState.Loading -> {
                    CircularProgressIndicator(
                        Modifier.size(48.dp),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 2.dp
                    )
                }
                else -> {
                    //nothing
                }
            }

        }
    }
}


