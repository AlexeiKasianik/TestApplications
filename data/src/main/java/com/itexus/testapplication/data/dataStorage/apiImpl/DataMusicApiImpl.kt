package com.itexus.testapplication.data.dataStorage.apiImpl

import android.content.Context
import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.dataStorage.realmModels.FeedRealm
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import io.realm.Realm
import io.realm.Realm.setDefaultConfiguration
import io.realm.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DataMusicApiImpl(private val context: Context) : DataMusicApi {


    private val realm: Realm = Realm.getDefaultInstance()

    override suspend fun getAlbums(): Flow<AlbumsRealm> {
        return realm.where<AlbumsRealm>().findAll().asFlow()
    }

    override suspend fun saveAlbums(albums: AlbumsRealm) {
        realm.executeTransactionAwait(Dispatchers.IO) {
            it.copyToRealmOrUpdate(albums)
        }
    }

}
