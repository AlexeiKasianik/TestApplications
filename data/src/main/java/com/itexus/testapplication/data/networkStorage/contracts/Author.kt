package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    @SerialName("name") val name: String = "",
    @SerialName("url") val url: String = "",
)