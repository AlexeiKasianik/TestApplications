package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import com.itexus.testapplication.presentation.logic.mapping.toPresentation
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.AllAlbumsScreenState
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.BaseAllAlbumsScreenViewModel
import com.itexus.testapplications.uiKit.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AllAlbumsScreenViewModel(
    private val router: Router,
    private val storageRepository: StorageRepository
) : BaseAllAlbumsScreenViewModel() {

    private val _state = MutableStateFlow(AllAlbumsScreenState())
    override val state: StateFlow<AllAlbumsScreenState> = _state


    init {

        viewModelScope.launch(exception = ::handleException) {
            _state.update { it.copy(isLoaderVisible = true) }
            val albums = storageRepository.getAlbums()
            _state.update {
                it.copy(
                    allAlbums = albums.feed.toPresentation(),
                    isLoaderVisible = false
                )
            }
        }
    }

    private fun handleException(throwable: Throwable) {
        _state.update { it.copy(isLoaderVisible = false, error = throwable.toString()) }
    }
}
