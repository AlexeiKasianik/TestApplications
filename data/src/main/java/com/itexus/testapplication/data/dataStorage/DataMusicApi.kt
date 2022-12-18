package com.itexus.testapplication.data.dataStorage

import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.dataStorage.realmModels.FeedRealm
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import io.realm.RealmResults
import kotlinx.coroutines.flow.Flow

interface DataMusicApi {

    suspend fun getAlbums() : Flow<AlbumsRealm>

    suspend fun saveAlbums(albums: AlbumsRealm)

}