package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("genreId") val genreId: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("url") val url: String = "",
)