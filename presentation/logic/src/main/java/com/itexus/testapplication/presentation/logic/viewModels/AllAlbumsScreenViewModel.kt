package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import com.itexus.testapplication.presentation.logic.mapping.toPresentation
import com.itexus.testapplication.presentation.ui.AlbumsUiState
import com.itexus.testapplication.presentation.ui.screens.BaseAllAlbumsScreenViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllAlbumsScreenViewModel(
    private val router: Router,
    private val storageRepository: StorageRepository
) : BaseAllAlbumsScreenViewModel() {

    private val _albums = MutableStateFlow<AlbumsUiState>(AlbumsUiState.Loading)
    override val albums: StateFlow<AlbumsUiState> = _albums

    /*сделать стейты sealed interface Loading Failure Success*/

    init {
        viewModelScope.launch {
            storageRepository.getAlbums()
                .map {
                    Napier.e("skfjvnkldfjnskvdfjnv")
                    it.toPresentation()
                }
                .onEach { _albums.value = AlbumsUiState.Loaded(it) }
                .catch {
                    Napier.e("skfjvnkldfjnskvdfjnv")
                    Napier.e(it.toString())
                    _albums.value = AlbumsUiState.Error(it.toString())
                }/*
                .flowOn(Dispatchers.IO)*/
                .launchIn(this)
        }
    }
}