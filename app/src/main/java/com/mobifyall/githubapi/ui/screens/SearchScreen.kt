package com.mobifyall.githubapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.commons.TAG_SEARCH_INPUT
import com.mobifyall.githubapi.commons.emptyString
import com.mobifyall.githubapi.ui.theme.Typography
import com.mobifyall.githubapi.viewmodels.HomeViewModel
import com.mobifyall.githubapi.viewstates.SearchViewState

@Composable
fun SearchScreen(viewModel: HomeViewModel, onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val searchBarUIState by viewModel.searchBarUIState.collectAsState()
    Scaffold(Modifier,
        topBar = {
            TopAppBar(modifier = Modifier,
                title = {}, //no title
                actions = {
                    searchInput(
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
                }
                is SearchViewState.Error -> {
                    ErrorUIComponent((uiState as SearchViewState.Error).errorText)
                }
                is SearchViewState.Loading -> {

                }
                else -> {
                    //nothing
                }
            }

        }
    }
}

@Composable
fun ErrorUIComponent(errorText: String) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.no_data),
            contentDescription = "Error description image.",
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp, 100.dp)
        )
        Text(
            text = errorText, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp)
        )
    }
}

@Composable
fun searchInput(uiState: SearchBarUIState, updateInput: (String) -> Unit, doneClicked: () -> Unit) {
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = uiState.inputState,
        onValueChange = updateInput,
        textStyle = Typography.body1,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 40.dp)
            .testTag(TAG_SEARCH_INPUT),
        maxLines = 1,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            doneClicked.invoke()
        }),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        decorationBox = { innerTextField ->
            Column(
                Modifier
                    .padding(8.dp),
            ) {
                innerTextField() //
                //todo add clear button
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


data class SearchBarUIState(var inputState: String = emptyString())