package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.data.useCases.GetAlbumsUseCase
import com.itexus.testapplication.data.useCases.GetAlbumUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCases = module {

    singleOf(::GetAlbumsUseCase)
    singleOf(::GetAlbumUseCase)
}
