package com.itexus.testapplication.di.features.mainActivity

import com.itexus.testapplication.presentation.logic.mainActivity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val activityModule = module {

    viewModelOf(::MainViewModel)

}