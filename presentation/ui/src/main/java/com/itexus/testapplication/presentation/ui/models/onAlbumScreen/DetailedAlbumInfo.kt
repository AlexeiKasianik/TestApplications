package com.itexus.testapplication.presentation.ui.models.onAlbumScreen

import java.util.Date

data class DetailedAlbumInfo(
    val artworkUrl: String = "",
    var artistName: String = "",
    var albumName: String = "",
    var genres: List<Genre> = listOf(),
    var albumUrl: String = "",
    var releaseDate: Date = Date()
)
