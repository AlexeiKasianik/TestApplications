package com.itexus.testapplication.presentation.ui.screens

import androidx.lifecycle.ViewModel
import com.itexus.testapplication.presentation.ui.models.AlbumsUiModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseAllAlbumsScreenViewModel : ViewModel() {

    abstract val albums: MutableStateFlow<List<AlbumsUiModel>>

}