package com.itexus.testapplication.presentation.logic.navigation.decorators

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator

class AppNavigatorDecorator(
    private val decorated: Navigator
) : Navigator {

    override fun applyCommands(commands: Array<out Command>) {
        decorated.applyCommands(commands)
    }
}
