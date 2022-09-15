package com.mobifyall.githubapi.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.ui.theme.Typography
import com.mobifyall.githubapi.viewstates.RepoUIState

@Composable
fun RowGitHubRepoUIComponent(item: RepoUIState, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Text(
            text = item.name,
            style = Typography.body1, modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = item.subHeading,
            style = Typography.body2, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Row(Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(id = R.string.content_description_stars_icon),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = item.stars,
                style = Typography.body2, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp)
            )
        }

    }
}