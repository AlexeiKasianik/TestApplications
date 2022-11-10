package com.itexus.testapplication.data.dataStorage

import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import io.realm.RealmResults

interface DataMusicApi {

    suspend fun getAlbums() : RealmResults<AlbumsRealm>?

    suspend fun saveAlbums(albums: Albums)

}