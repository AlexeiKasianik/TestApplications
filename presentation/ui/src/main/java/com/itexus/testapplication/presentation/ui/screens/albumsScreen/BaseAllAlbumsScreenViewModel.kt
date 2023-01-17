package com.itexus.testapplication.presentation.ui.screens.albumsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAllAlbumsScreenViewModel : ViewModel() {

    // output
    abstract val state: StateFlow<AllAlbumsScreenState>
    abstract fun reloadData()

    // input
    abstract fun albumClick(albumId: String)

}
