package com.mobifyall.githubapi.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.commons.TAG_HEADER_TITLE
import com.mobifyall.githubapi.ui.theme.Typography

@Composable
fun HeaderUIComponent(text: String) {
    Text(
        text = text,
        style = Typography.h5, modifier = Modifier.testTag(TAG_HEADER_TITLE)
            .fillMaxWidth()
            .padding(16.dp, 16.dp)
    )
}