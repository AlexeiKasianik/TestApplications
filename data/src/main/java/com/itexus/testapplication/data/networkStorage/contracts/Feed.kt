package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    @SerialName("id") val id: String = "",
    @SerialName("author") val author: Author = Author(),
    @SerialName("copyright") val copyright: String = "",
    @SerialName("country") val country: String = "",
    @SerialName("icon") val icon: String = "",
    @SerialName("links") val links: List<Link> = listOf(),
    @SerialName("results") val results: List<Result> = listOf(),
    @SerialName("title") val title: String = "",
    @SerialName("updated") val updated: String = "",
)