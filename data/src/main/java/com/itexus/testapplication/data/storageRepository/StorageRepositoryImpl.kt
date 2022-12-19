package com.itexus.testapplication.data.storageRepository

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.realmModels.AlbumsRealm
import com.itexus.testapplication.data.mapping.toDomain
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.data.networkStorage.contracts.Albums
import com.itexus.testapplication.domain.exceptions.LoadingDataException
import com.itexus.testapplication.domain.models.AlbumsEntity
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import kotlinx.coroutines.flow.*
import java.net.UnknownHostException

class StorageRepositoryImpl(
    private val dbApi: DataMusicApi,
    private val networkApi: NetworkMusicApi
) : StorageRepository {

    override suspend fun getAlbums(): Flow<AlbumsEntity> {
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
