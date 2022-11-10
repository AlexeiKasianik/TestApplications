package com.itexus.testapplication.domain.storageRepository

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.domain.models.AllAlbums

class StorageRepository(
    private val dbApi: DataMusicApi,
    private val networkApi: NetworkMusicApi
) {

    suspend fun getAlbums(): List<AllAlbums> {
        return networkApi.getAlbums().feed.results.map {
            AllAlbums(it.artworkUrl)
        }
    }

}
