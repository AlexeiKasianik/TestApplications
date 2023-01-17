package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.data.useCases.GetAlbumsUseCase
import com.itexus.testapplication.presentation.logic.mapping.toPresentation
import com.itexus.testapplication.presentation.logic.navigation.Screens.buildOneAlbumScreen
import com.itexus.testapplication.presentation.ui.R
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.AllAlbumsScreenState
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.BaseAllAlbumsScreenViewModel
import com.itexus.testapplications.uiKit.launch
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AllAlbumsScreenViewModel(
    private val router: Router,
    private val getAlbumsUseCase: GetAlbumsUseCase
) : BaseAllAlbumsScreenViewModel() {

    private val _state = MutableStateFlow(AllAlbumsScreenState())
    override val state: StateFlow<AllAlbumsScreenState> = _state

    init {
        loadData()
    }

    override fun albumClick(albumId: String) {

        router.navigateTo(
            buildOneAlbumScreen(
                albumId = albumId,
                copyright = state.value.allAlbums!!.copyright
            )
        )
    }

    override fun reloadData() = loadData()

    private fun loadData() {
        viewModelScope.launch(
            context = Dispatchers.IO,
            exception = ::handleException
        ) {
            _state.update { it.copy(isLoaderVisible = true, error = null) }
            val albums = getAlbumsUseCase()
            _state.update {
                it.copy(
                    allAlbums = albums.feed.toPresentation(),
                    isLoaderVisible = false
                )
            }
        }
    }

    private fun handleException(throwable: Throwable) {
        Napier.e { throwable.toString() }
        _state.update { it.copy(isLoaderVisible = false, error = R.string.error_message) }
    }
}
