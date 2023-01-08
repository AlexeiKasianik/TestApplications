package com.itexus.testapplication.data.dataStorage.apiImpl

import android.content.Context
import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.*
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.RealmConfiguration

class DataMusicApiImpl() : DataMusicApi {


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
        //todo need create exception
        return realm.query<AlbumsRealm>().first().find() ?: throw RuntimeException("Empty database")
    }

    override suspend fun saveAlbums(albums: AlbumsRealm) {
        realm.write {
            copyToRealm(albums)
        }
    }

}
