package com.mobifyall.githubapi

import com.mobifyall.githubapi.core.models.Owner
import com.mobifyall.githubapi.core.models.Repository

object FakeData {
    val owner = Owner(
        id = 1342004,
        avatar_url = "https://avatars.githubusercontent.com/u/1342004?v=4\"",
        node_id = "MDEyOk9yZ2FuaXphdGlvbjEzNDIwMDQ",
        url = "https://api.github.com/users/google"
    )
    val repoList = mutableListOf<Repository>().apply {
        add(
            Repository(
                id = 1, node_id = "MDEwOlJlcG9zaXRvcnkyNDk1MzQ0OA",
                name = "material-design-icons",
                full_name = "google/material-design-icons",
                owner = owner,
                description = "Material Design icons by Google",
                stargazers_count = 46467,
                watchers_count = 46467,
                html_url = "https://github.com/google/material-design-icons"
            )
        )
    }
}