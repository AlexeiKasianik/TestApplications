package com.itexus.testapplication.presentation.logic.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.NavigatorHolder
import com.itexus.testapplication.presentation.logic.navigation.decorators.AppNavigatorDecorator
import com.mbanq.sky.presentation.logic.navigation.navigators.CustomSupportAppNavigator

open class FragmentNavigator<T : AppCompatActivity>(
    private val navigatorHolder: NavigatorHolder,
    @IdRes private val appContainerId: Int
) : INavigator<T> {

    override fun attach(subject: T) = with(subject) {
        lifecycle.addObserver(object : DefaultLifecycleObserver {

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                val navigator = AppNavigatorDecorator(
                    decorated = CustomSupportAppNavigator(subject, appContainerId)
                )
                navigatorHolder.setNavigator(navigator)
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                navigatorHolder.removeNavigator()
            }
        })
    }
}
