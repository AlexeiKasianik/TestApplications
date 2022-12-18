package com.itexus.testapplication.presentation.ui.models

data class ResultUI(
    val artistId: String = "",
    val artistName: String = "",
    val artistUrl: String = "",
    val artworkUrl: String = "",
    val contentAdvisoryRating: String = "",
    val genres: List<GenreUI> = listOf(),
    val id: String = "",
    val kind: String = "",
    val name: String = "",
    val releaseDate: String = "",
    val url: String = "",
)