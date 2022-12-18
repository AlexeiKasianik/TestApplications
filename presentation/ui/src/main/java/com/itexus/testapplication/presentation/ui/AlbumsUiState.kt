package com.itexus.testapplication.presentation.ui

import com.itexus.testapplication.presentation.ui.models.AlbumsUiModel

sealed class AlbumsUiState {
    object Loading : AlbumsUiState()
    class Loaded(val albumsUiModel: AlbumsUiModel) : AlbumsUiState()
    class Error(val message: String) : AlbumsUiState()
}