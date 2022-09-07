package com.mobifyall.githubapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.ui.theme.Purple500
import com.mobifyall.githubapi.viewmodels.HomeViewModel

@Composable
fun SearchScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(Modifier, topBar = {
        TopAppBar(modifier = Modifier, backgroundColor = Purple500, title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        actions = {
            searchInput(uiState)
        })
    }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(1f)
                .background(Color.White),
        ) {


        }
    }
}