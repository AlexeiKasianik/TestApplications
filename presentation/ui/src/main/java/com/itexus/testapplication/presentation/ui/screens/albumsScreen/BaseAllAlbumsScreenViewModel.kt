package com.itexus.testapplication.presentation.ui.screens.albumsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAllAlbumsScreenViewModel : ViewModel() {

    abstract val state: StateFlow<AllAlbumsScreenState>

}