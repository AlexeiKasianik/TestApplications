package com.itexus.testapplication.domain.models

data class ResultEntity(
    val artistId: String = "",
    val artistName: String = "",
    val artistUrl: String = "",
    val artworkUrl: String = "",
    val contentAdvisoryRating: String = "",
    val genres: List<GenreEntity> = listOf(),
    val id: String = "",
    val kind: String = "",
    val name: String = "",
    val releaseDate: String = "",
    val url: String = "",
)