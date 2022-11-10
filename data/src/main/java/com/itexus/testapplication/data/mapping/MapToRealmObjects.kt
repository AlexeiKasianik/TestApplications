package com.itexus.testapplication.data.mapping

import com.itexus.testapplication.data.dataStorage.realmModels.*
import com.itexus.testapplication.data.networkStorage.contracts.*
import io.realm.RealmList

internal fun Albums.toRealmAlbums() = AlbumsRealm()
    .also {
        it.feed = feed.toRealmFeed()
    }


internal fun Feed.toRealmFeed() = FeedRealm().also {
    it.author = author.toRealmAuthor()
    it.id = id
    it.copyright = copyright
    it.icon = icon
    it.country = country
    it.title = title
    it.updated = updated
    it.links = RealmList<LinkRealm>().apply {
        addAll(
            links.map {
                it.toRealmLink()
            })
    }
    it.results = RealmList<ResultRealm>().apply {
        addAll(
            results.map {
                it.toRealmResult()
            }
        )
    }
}

internal fun Author.toRealmAuthor() = AuthorRealm().also {
    it.url = url
    it.name = name
}

internal fun Link.toRealmLink() = LinkRealm().also {
    it.self = self
}

internal fun Result.toRealmResult() = ResultRealm().also {
    it.artistId = artistId
    it.artistName = artistName
    it.artistUrl = artistUrl
    it.artworkUrl = artworkUrl
    it.contentAdvisoryRating = contentAdvisoryRating
    it.genres = RealmList<GenreRealm>().apply {
        addAll(genres.map {
            it.toRealmGenre()
        })
    }
    it.id = id
    it.kind = kind
    it.name = name
    it.releaseDate = releaseDate
    it.url = url
}

internal fun Genre.toRealmGenre() = GenreRealm().also {
    it.genreId = genreId
    it.name = name
    it.url = url
}