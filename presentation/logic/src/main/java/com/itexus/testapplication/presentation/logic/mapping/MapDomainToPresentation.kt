package com.itexus.testapplication.presentation.logic.mapping

import com.itexus.testapplication.domain.models.AllAlbums
import com.itexus.testapplication.presentation.ui.models.AlbumsUiModel

fun List<AllAlbums>.toPresentation() = map {
    AlbumsUiModel(albumUrl = it.albumImageUrl)
}