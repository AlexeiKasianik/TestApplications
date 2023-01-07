package com.itexus.testapplication.presentation.logic.mapping

import com.itexus.testapplication.domain.models.*
import com.itexus.testapplication.presentation.ui.models.*
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.AllAlbumsScreenState

internal fun AlbumsEntity.toPresentation() = AllAlbumsScreenState(
    allAlbums = feed.toPresentation(),
)

internal fun FeedEntity.toPresentation() = FeedUI(
    results = results.map { it.toPresentation() },
)

internal fun ResultEntity.toPresentation() = AlbumInfo(
    artistName = artistName,
    artworkUrl = artworkUrl,
    name = name,
)
