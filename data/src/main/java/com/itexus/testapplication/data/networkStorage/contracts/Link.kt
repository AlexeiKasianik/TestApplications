package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("self") val self: String,
)