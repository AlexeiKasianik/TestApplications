package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.domain.storageRepository.StorageRepository
import com.itexus.testapplication.presentation.logic.mapping.toPresentation
import com.itexus.testapplication.presentation.ui.screens.AllAlbumsScreenState
import com.itexus.testapplication.presentation.ui.screens.BaseAllAlbumsScreenViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllAlbumsScreenViewModel(
    private val router: Router,
    private val storageRepository: StorageRepository
) : BaseAllAlbumsScreenViewModel() {

    private val _state = MutableStateFlow(AllAlbumsScreenState())
    override val state: StateFlow<AllAlbumsScreenState> = _state


    init {


        viewModelScope.launch {

            try {
                _state.update { it.copy(isLoaderVisible = true) }

                val albums = storageRepository.getAlbums()

                _state.update {
                    it.copy(
                        allAlbums = albums.feed.toPresentation(),
                        isLoaderVisible = false
                    )
                }
            } catch (e: Exception) {
                Napier.e(e.toString())
                _state.update { it.copy(isLoaderVisible = false, error = e.toString()) }
            }
        }
    }
}
