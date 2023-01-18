package com.itexus.testapplication.domain.useCases

import com.itexus.testapplication.domain.models.AlbumsEntity

interface GetAlbumsUseCase {

    suspend operator fun invoke(): AlbumsEntity

}