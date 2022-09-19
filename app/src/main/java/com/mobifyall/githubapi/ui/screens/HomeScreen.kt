package com.mobifyall.githubapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.commons.TAG_SEARCH_ICON
import com.mobifyall.githubapi.navigation.AppNav


@Composable
fun HomeScreen(searchClicked: () ->Unit) {
    Scaffold(Modifier, topBar = {
        TopAppBar(modifier = Modifier, title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        }, actions = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = stringResource(
                    id = R.string.content_description_search_icon
                ),
                modifier = Modifier.testTag(TAG_SEARCH_ICON)
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .clickable {
                        searchClicked.invoke()
                    })
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(id = R.string.no_recent_searches))
        }
    }

}