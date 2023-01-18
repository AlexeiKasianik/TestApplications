package com.itexus.testapplication.domain.useCases

import com.itexus.testapplication.domain.models.ResultEntity

interface GetAlbumUseCase {

    suspend operator fun invoke(id: String): ResultEntity

}