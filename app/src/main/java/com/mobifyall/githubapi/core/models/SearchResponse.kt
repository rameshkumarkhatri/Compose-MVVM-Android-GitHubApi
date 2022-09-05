package com.mobifyall.githubapi.core.models

//we can use @SerializedName("name") too
data class SearchResponse(
    val total_count: Long,
    val items: List<Repository>,
)

data class Repository(
    val id: Long, val node_id: String? = null,
    val name: String? = null,
    val full_name: String? = null,
    val owner: Owner? = null,
    val description: String? = null,
    val stargazers_count: Int = 0,
    val watchers_count: Int = 0,
    val html_url: String? = null,
)

data class Owner(
    val id: Long,
    val avatar_url: String? = null,
    val node_id: String? = null,
    val url: String? = null,
)