package com.itexus.testapplication.presentation.logic.mainActivity

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.presentation.logic.navigation.Screens.allAlbumsScreen
import io.github.aakira.napier.Napier

class MainViewModel(
    private val router: Router
) : ViewModel() {

    init {
        router.replaceScreen(allAlbumsScreen)
    }

}
