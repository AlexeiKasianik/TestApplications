package com.itexus.testapplication.data.mapping

import com.itexus.testapplication.data.dataStorage.realmModels.*
import com.itexus.testapplication.domain.exceptions.EmptyDBException
import com.itexus.testapplication.domain.models.*

internal fun AlbumsRealm.toDomain() = AlbumsEntity(
    feed = feed?.toDomain() ?: throw EmptyDBException(),
)

internal fun FeedRealm.toDomain() = FeedEntity(
    id = id,
    author = author?.toDomain() ?: throw EmptyDBException(),
    copyright = copyright,
    country = country,
    icon = icon,
    links = links.map { it.toDomain() },
    results = results.map { it.toDomain() },
)

internal fun AuthorRealm.toDomain() = AuthorEntity(
    name = name,
    url = url,
)

internal fun LinkRealm.toDomain() = LinkEntity(
    self = self,
)

internal fun ResultRealm.toDomain() = ResultEntity(
    artistId = artistId,
    artistName = artistName,
    artistUrl = artistUrl,
    artworkUrl = artworkUrl,
    contentAdvisoryRating = contentAdvisoryRating,
    genres = genres.map { it.toDomain() },
    id = id,
    kind = kind,
    name = name,
    releaseDate = releaseDate,
    url = url,
)

internal fun GenreRealm.toDomain() = GenreEntity(
    genreId = genreId,
    name = name,
)
