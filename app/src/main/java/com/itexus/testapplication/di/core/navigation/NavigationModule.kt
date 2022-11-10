package com.itexus.testapplication.di.core.navigation

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.presentation.logic.navigation.FragmentNavigator
import com.itexus.testapplication.presentation.logic.navigation.INavigator
import org.koin.dsl.binds
import org.koin.dsl.module

val navigationModule = module {

    single { Cicerone.create(Router()) }

    factory { get<Cicerone<Router>>().getNavigatorHolder() }

    factory { get<Cicerone<Router>>().router }.binds(arrayOf(Router::class))

    factory<INavigator<AppCompatActivity>> { (appContainerId: Int) ->
        FragmentNavigator(
            navigatorHolder = get(),
            appContainerId = appContainerId
        )
    }
}
