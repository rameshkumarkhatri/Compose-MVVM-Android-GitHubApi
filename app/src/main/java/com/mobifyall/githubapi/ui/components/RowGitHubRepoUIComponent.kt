package com.mobifyall.githubapi.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.ui.theme.Typography
import com.mobifyall.githubapi.viewstates.RepoUIState

@Composable
fun RowGitHubRepoUIComponent(item: RepoUIState, onClick: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(item.url)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = item.name,
            style = Typography.h6.copy(MaterialTheme.colors.primary), modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = item.subHeading,
            style = Typography.body2, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(id = R.string.content_description_stars_icon),
                modifier = Modifier,

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