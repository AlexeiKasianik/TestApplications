package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.data.storageRepository.StorageRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCases = module {

}

val repositories = module {
    singleOf(::StorageRepositoryImpl)
}

val domainModule = useCases + repositories