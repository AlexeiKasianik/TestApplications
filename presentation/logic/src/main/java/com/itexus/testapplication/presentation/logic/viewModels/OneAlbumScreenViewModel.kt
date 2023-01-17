package com.itexus.testapplication.presentation.logic.viewModels

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.itexus.testapplication.data.useCases.GetAlbumUseCase
import com.itexus.testapplication.presentation.logic.mapping.toAlbumPresentation
import com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen.BaseOneAlbumScreenViewModel
import com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen.OneAlbumScreenState
import com.itexus.testapplications.uiKit.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class OneAlbumScreenViewModel(
    private val albumId: String,
    private val copyright: String,
    private val getAlbumUseCase: GetAlbumUseCase,
    private val router: Router,
) : BaseOneAlbumScreenViewModel() {

    private val _state = MutableStateFlow(OneAlbumScreenState())
    override val state: StateFlow<OneAlbumScreenState> = _state

    init {
        loadData()
    }

    override fun actionBack() = router.exit()

    private fun loadData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val albums = getAlbumUseCase(albumId)
            _state.update {
                it.copy(
                    detailedAlbumInfo = albums.toAlbumPresentation(),
                    copyright = copyright
                )
            }
        }
    }

    companion object {
        const val ALBUM_ID = "album_id"
        const val COPYRIGHT = "copyright"
    }
}
