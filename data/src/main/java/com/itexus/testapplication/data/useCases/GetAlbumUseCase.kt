package com.itexus.testapplication.data.useCases

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.mapping.toDomain
import com.itexus.testapplication.domain.models.ResultEntity

class GetAlbumUseCase(
    private val dbApi: DataMusicApi,
) {

    suspend operator fun invoke(id: String): ResultEntity {
        return dbApi.getAlbum(id).toDomain()
    }

}
