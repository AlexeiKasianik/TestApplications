package com.itexus.testapplication.presentation.logic.mapping

import com.itexus.testapplication.domain.models.*
import com.itexus.testapplication.presentation.ui.models.alAlbumsScreen.AlbumInfo
import com.itexus.testapplication.presentation.ui.models.alAlbumsScreen.FeedUI

internal fun FeedEntity.toPresentation() = FeedUI(
    results = results.map { it.toPresentation() },
    copyright = copyright
)

internal fun ResultEntity.toPresentation() = AlbumInfo(
    id = id,
    artistName = artistName,
    artworkUrl = artworkUrl,
    name = name,
)
