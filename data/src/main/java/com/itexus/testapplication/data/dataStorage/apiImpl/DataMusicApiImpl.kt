package com.itexus.testapplication.data.dataStorage.apiImpl

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where

class DataMusicApiImpl: DataMusicApi {

    private val realm: Realm = Realm.getDefaultInstance()

    init {
        val config = RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }

    override suspend fun getAlbums(): RealmResults<AlbumsRealm>? {
        return realm.where<AlbumsRealm>().findAll()
    }

    override suspend fun saveAlbums(albums: Albums) {
        realm.executeTransaction {
            albums.toRealmAlbums().feed?.let {
                realm.copyToRealmOrUpdate(it)
            }
        }
    }

}
