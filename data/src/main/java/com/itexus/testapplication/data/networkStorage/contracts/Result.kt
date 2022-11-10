package com.itexus.testapplication.data.networkStorage.contracts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("artistId") val artistId: String = "",
    @SerialName("artistName") val artistName: String = "",
    @SerialName("artistUrl") val artistUrl: String = "",
    @SerialName("artworkUrl100") val artworkUrl: String = "",
    @SerialName("contentAdvisoryRating") val contentAdvisoryRating: String = "",
    @SerialName("genres") val genres: List<Genre> = listOf(),
    @SerialName("id") val id: String = "",
    @SerialName("kind") val kind: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("releaseDate") val releaseDate: String = "",
    @SerialName("url") val url: String = "",
)