package com.itexus.testapplication.presentation.ui.screens.albumsScreen

import com.itexus.testapplication.presentation.ui.models.alAlbumsScreen.FeedUI

data class AllAlbumsScreenState(
    val allAlbums: FeedUI? = null,
    val isLoaderVisible: Boolean = true,
    val error: Int? = null
)
