package com.itexus.testapplication.di.features.musicFeature

import com.itexus.testapplication.presentation.logic.viewModels.AllAlbumsScreenViewModel
import com.itexus.testapplication.presentation.ui.screens.BaseAllAlbumsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(::AllAlbumsScreenViewModel) bind BaseAllAlbumsScreenViewModel::class

}