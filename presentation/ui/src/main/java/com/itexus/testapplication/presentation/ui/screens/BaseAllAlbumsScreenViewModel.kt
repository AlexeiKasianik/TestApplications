package com.itexus.testapplication.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.itexus.testapplication.presentation.ui.AlbumsUiState
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAllAlbumsScreenViewModel : ViewModel() {

    abstract val albums: StateFlow<AlbumsUiState>

}