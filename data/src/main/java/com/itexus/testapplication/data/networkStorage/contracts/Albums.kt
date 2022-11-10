package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Albums(
    @SerialName("feed") val feed: Feed = Feed(),
)
