package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.data.storageRepository.StorageRepositoryImpl
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val useCases = module {

}

val repositories = module {
    singleOf(::StorageRepositoryImpl) bind StorageRepository::class
}

val domainModule = useCases + repositories