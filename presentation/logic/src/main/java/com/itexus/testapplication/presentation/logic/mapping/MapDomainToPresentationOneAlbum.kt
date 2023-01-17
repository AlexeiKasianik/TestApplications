package com.itexus.testapplication.presentation.logic.mapping

import com.itexus.testapplication.domain.models.GenreEntity
import com.itexus.testapplication.domain.models.ResultEntity
import com.itexus.testapplication.presentation.ui.models.onAlbumScreen.DetailedAlbumInfo
import com.itexus.testapplication.presentation.ui.models.onAlbumScreen.Genre

internal fun ResultEntity.toAlbumPresentation() = DetailedAlbumInfo(
    artworkUrl = artworkUrl,
    artistName = artistName,
    albumName = name,
    genres = genres.toAlbumPresentation(),
    albumUrl = url
)

internal fun List<GenreEntity>.toAlbumPresentation() = map {
    Genre(name = it.name)
}
