package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.data.dataStorage.DataMusicApi
import com.itexus.testapplication.data.dataStorage.apiImpl.DataMusicApiImpl
import com.itexus.testapplication.data.networkStorage.NetworkMusicApi
import com.itexus.testapplication.data.networkStorage.apiImpl.NetworkMusicApiIml
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    singleOf(::NetworkMusicApiIml) bind NetworkMusicApi::class

    singleOf(::DataMusicApiImpl) bind DataMusicApi::class

}
