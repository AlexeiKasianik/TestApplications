package com.itexus.testapplication.data.storageRepository

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.mapping.toDomain
import com.itexus.testapplication.data.mapping.toRealmAlbums
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.domain.exceptions.LoadingDataException
import com.itexus.testapplication.domain.models.AlbumsEntity
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import io.github.aakira.napier.Napier
import java.net.UnknownHostException

class StorageRepositoryImpl(
    private val dbApi: DataMusicApi,
    private val networkApi: NetworkMusicApi
) : StorageRepository {

    override suspend fun getAlbums(): AlbumsEntity {
        return try {
            val networkAlbums = networkApi.getAlbums().also {
                dbApi.saveAlbums(it.toRealmAlbums())
            }
            networkAlbums.toDomain()
        } catch (e: Throwable) {
            Napier.e(e.toString())
            e.handleError()
        }
    }

    private suspend fun Throwable.handleError(): AlbumsEntity {
        return if (this is UnknownHostException) dbApi.getAlbums().toDomain()
        else throw LoadingDataException(123)
    }

}
