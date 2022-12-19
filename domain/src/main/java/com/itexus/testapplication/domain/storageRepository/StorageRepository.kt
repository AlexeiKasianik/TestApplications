package com.itexus.testapplication.domain.storageRepository

import com.itexus.testapplication.domain.models.AlbumsEntity

interface StorageRepository {

    suspend fun getAlbums(): AlbumsEntity

}
