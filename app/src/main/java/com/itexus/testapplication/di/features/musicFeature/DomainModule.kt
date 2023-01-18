package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.data.useCases.GetAlbumsUseCaseImpl
import com.itexus.testapplication.data.useCases.GetAlbumUseCaseImpl
import com.itexus.testapplication.domain.useCases.GetAlbumUseCase
import com.itexus.testapplication.domain.useCases.GetAlbumsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCases = module {

    singleOf(::GetAlbumsUseCaseImpl) bind GetAlbumsUseCase::class
    singleOf(::GetAlbumUseCaseImpl) bind GetAlbumUseCase::class
}
