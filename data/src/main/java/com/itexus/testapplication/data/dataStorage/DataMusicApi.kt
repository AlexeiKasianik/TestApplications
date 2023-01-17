package com.itexus.testapplication.data.dataStorage

import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.dataStorage.realmModels.ResultRealm

interface DataMusicApi {

    suspend fun getAlbums(): AlbumsRealm

    suspend fun saveAlbums(albums: AlbumsRealm)

    suspend fun getAlbum(id: String): ResultRealm

}