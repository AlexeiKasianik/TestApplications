package com.itexus.testapplication.presentation.logic.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.itexus.testapplication.presentation.logic.viewModels.OneAlbumScreenViewModel.Companion.ALBUM_ID
import com.itexus.testapplication.presentation.logic.viewModels.OneAlbumScreenViewModel.Companion.COPYRIGHT
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.AllAlbumsScreen
import com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen.OneAlbumScreen
import com.itexus.testapplications.uiKit.withArguments

object Screens {

    val allAlbumsScreen = FragmentScreen { AllAlbumsScreen() }

    fun buildOneAlbumScreen(albumId: String, copyright: String) = FragmentScreen {
        OneAlbumScreen().withArguments {
            it.putString(ALBUM_ID, albumId)
            it.putString(COPYRIGHT, copyright)
        }
    }
}
