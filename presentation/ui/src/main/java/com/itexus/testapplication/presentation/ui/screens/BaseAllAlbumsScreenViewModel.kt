package com.itexus.testapplication.presentation.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAllAlbumsScreenViewModel : ViewModel() {

    abstract val state: StateFlow<AllAlbumsScreenState>

}