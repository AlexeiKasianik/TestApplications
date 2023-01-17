package com.itexus.testapplication.di.features.musicFeature

import android.os.Bundle
import com.itexus.testapplication.presentation.logic.viewModels.AllAlbumsScreenViewModel
import com.itexus.testapplication.presentation.logic.viewModels.OneAlbumScreenViewModel
import com.itexus.testapplication.presentation.logic.viewModels.OneAlbumScreenViewModel.Companion.ALBUM_ID
import com.itexus.testapplication.presentation.logic.viewModels.OneAlbumScreenViewModel.Companion.COPYRIGHT
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.BaseAllAlbumsScreenViewModel
import com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen.BaseOneAlbumScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(::AllAlbumsScreenViewModel) bind BaseAllAlbumsScreenViewModel::class

    viewModel<BaseOneAlbumScreenViewModel> { (args: Bundle) ->
        OneAlbumScreenViewModel(
            albumId = args.getString(ALBUM_ID).orEmpty(),
            copyright = args.getString(COPYRIGHT).orEmpty(),
            getAlbumUseCase = get(),
            router = get(),
        )
    }
}
