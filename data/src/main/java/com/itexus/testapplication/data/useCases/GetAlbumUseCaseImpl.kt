package com.itexus.testapplication.data.useCases

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.mapping.toDomain
import com.itexus.testapplication.domain.models.ResultEntity
import com.itexus.testapplication.domain.useCases.GetAlbumUseCase

class GetAlbumUseCaseImpl(
    private val dbApi: DataMusicApi,
) : GetAlbumUseCase {

    override suspend operator fun invoke(id: String): ResultEntity {
        return dbApi.getAlbum(id).toDomain()
    }

}
