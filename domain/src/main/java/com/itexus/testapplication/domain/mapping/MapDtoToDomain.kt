package com.itexus.testapplication.domain.mapping

import com.itexus.testapplication.data.networkStorage.contracts.Albums
import com.itexus.testapplication.data.networkStorage.contracts.Author
import com.itexus.testapplication.data.networkStorage.contracts.Feed
import com.itexus.testapplication.data.networkStorage.contracts.Genre
import com.itexus.testapplication.data.networkStorage.contracts.Link
import com.itexus.testapplication.data.networkStorage.contracts.Result
import com.itexus.testapplication.domain.models.*

internal fun Albums.toDomain() = AlbumsEntity(
    feed = feed.toDomain()
)

internal fun Feed.toDomain() = FeedEntity(
    id = id,
    author = author.toDomain(),
    copyright = copyright,
    country = country,
    icon = icon,
    links = links.map { it.toDomain() },
    results = results.map { it.toDomain() },
    title = title,
)

internal fun Author.toDomain() = AuthorEntity(
    name = name,
    url = url
)

internal fun Link.toDomain() = LinkEntity(
    self = self
)

internal fun Result.toDomain() = ResultEntity(
    artistId = artistId,
    artistName = artistName,
    artistUrl = artistUrl,
    artworkUrl = artworkUrl,
    contentAdvisoryRating = contentAdvisoryRating,
    genres = genres.map { it.toDomain() },
    id = id,
    kind = kind,
    name = name,
    releaseDate = releaseDate
)

internal fun Genre.toDomain() = GenreEntity(
    genreId = genreId,
    name = name,
    url = url
)