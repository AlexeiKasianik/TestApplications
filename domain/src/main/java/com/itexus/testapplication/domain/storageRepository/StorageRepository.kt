package com.itexus.testapplication.domain.storageRepository

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import com.itexus.testapplication.domain.exceptions.LoadingDataException
import com.itexus.testapplication.domain.mapping.toDomain
import com.itexus.testapplication.domain.models.AlbumsEntity
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException

class StorageRepository(
    private val dbApi: DataMusicApi,
    private val networkApi: NetworkMusicApi
) {

    suspend fun getAlbums(): Flow<AlbumsEntity> {
        return flow { emit(networkApi.getAlbums()) }
            .map {
                dbApi.saveAlbums(it.toRealmAlbums())
                it
            }
            .map(Albums::toDomain)
            .catch { emit(it.handleError().first().toDomain()) }
    }

    private suspend fun Throwable.handleError(): Flow<AlbumsRealm> {
        return if (this is UnknownHostException) dbApi.getAlbums()
        else throw LoadingDataException(123)
    }

    /*сделать объет состояний success/failure (возможно вложенные sealed классы)*/
}
