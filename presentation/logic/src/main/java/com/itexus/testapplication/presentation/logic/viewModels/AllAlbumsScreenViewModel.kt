package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.domain.models.AllAlbums
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import com.itexus.testapplication.presentation.logic.mapping.toPresentation
import com.itexus.testapplication.presentation.ui.models.AlbumsUiModel
import com.itexus.testapplication.presentation.ui.screens.BaseAllAlbumsScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AllAlbumsScreenViewModel(
    private val router: Router,
    private val storageRepository: StorageRepository
) : BaseAllAlbumsScreenViewModel() {

    override val albums = MutableStateFlow<List<AlbumsUiModel>>(listOf())

    init {
        viewModelScope.launch {
            albums.value = storageRepository.getAlbums().toPresentation()
        }
    }

}