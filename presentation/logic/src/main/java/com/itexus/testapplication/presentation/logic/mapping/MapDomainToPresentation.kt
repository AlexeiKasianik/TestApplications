package com.itexus.testapplication.presentation.logic.mapping

import com.itexus.testapplication.domain.models.*
import com.itexus.testapplication.presentation.ui.models.*

internal fun AlbumsEntity.toPresentation() = AlbumsUiModel(
    feed = feed.toPresentation(),
)

internal fun FeedEntity.toPresentation() = FeedUI(
    id = id,
    author = author.toPresentation(),
    copyright = copyright,
    country = country,
    icon = icon,
    links = links.map { it.toPresentation() },
    results = results.map { it.toPresentation() },
    title = title,
    updated = updated,
)

internal fun LinkEntity.toPresentation() = LinkUI(
    self = self
)

internal fun ResultEntity.toPresentation() = ResultUI(
    artistId = artistId,
    artistName = artistName,
    artistUrl = artistUrl,
    artworkUrl = artworkUrl,
    contentAdvisoryRating = contentAdvisoryRating,
    genres = genres.map { it.toPresentation() },
    id = id,
    kind = kind,
    name = name,
    releaseDate = releaseDate,
    url = url
)

internal fun GenreEntity.toPresentation() = GenreUI(
    genreId = genreId,
    name = name,
    url = url
)

internal fun AuthorEntity.toPresentation() = AuthorUI(
    name = name,
    url = url
)