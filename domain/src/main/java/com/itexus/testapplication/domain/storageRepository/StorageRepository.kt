package com.itexus.testapplication.domain.storageRepository

import com.itexus.testapplication.domain.models.AlbumsEntity
import kotlinx.coroutines.flow.*

interface StorageRepository {

    suspend fun getAlbums(): Flow<AlbumsEntity>

}
