package com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseOneAlbumScreenViewModel : ViewModel() {

    // output
    abstract val state: StateFlow<OneAlbumScreenState>

    // input
    abstract fun actionBack()

}
