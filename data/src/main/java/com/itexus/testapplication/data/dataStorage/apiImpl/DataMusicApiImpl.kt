package com.itexus.testapplication.data.dataStorage.apiImpl

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.dataStorage.realmModels.AuthorRealm
import com.itexus.testapplication.data.dataStorage.realmModels.FeedRealm
import com.itexus.testapplication.data.dataStorage.realmModels.GenreRealm
import com.itexus.testapplication.data.dataStorage.realmModels.LinkRealm
import com.itexus.testapplication.data.dataStorage.realmModels.ResultRealm
import com.itexus.testapplication.domain.exceptions.EmptyDBException
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class DataMusicApiImpl : DataMusicApi {

    private val realm: Realm = Realm.open(
        RealmConfiguration.Builder(
            setOf(
                ResultRealm::class,
                LinkRealm::class,
                GenreRealm::class,
                FeedRealm::class,
                AuthorRealm::class,
                AlbumsRealm::class,
            )
        )
            .deleteRealmIfMigrationNeeded()
            .build()
    )

    override suspend fun getAlbums(): AlbumsRealm {
        return realm.query<AlbumsRealm>().first().find() ?: throw EmptyDBException()
    }

    override suspend fun saveAlbums(albums: AlbumsRealm) {
        realm.write {
            copyToRealm(albums)
        }
    }

    override suspend fun getAlbum(id: String): ResultRealm {
        return realm.query(clazz = ResultRealm::class, query = "id == $0", id).first().find()
            ?: throw EmptyDBException()
    }
}
