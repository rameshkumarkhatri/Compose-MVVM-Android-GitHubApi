package com.mobifyall.githubapi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.commons.TAG_ERROR_MESSAGE

@Composable
fun ErrorUIComponent(errorText: String) {
    Column(Modifier.fillMaxSize().testTag(TAG_ERROR_MESSAGE), verticalArrangement = Arrangement.Center) {
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