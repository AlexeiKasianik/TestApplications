package com.itexus.testapplication.data.dataStorage

import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm

interface DataMusicApi {

    suspend fun getAlbums() : AlbumsRealm

    suspend fun saveAlbums(albums: AlbumsRealm)

}