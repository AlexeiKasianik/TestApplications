package com.itexus.testapplication.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.itexus.testapplication.presentation.ui.AlbumsUiState
import com.itexus.testapplication.presentation.ui.compose.AlbumCard
import com.itexus.testapplication.presentation.ui.compose.MyLoader
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllAlbumsScreen : Fragment() {

    private val viewModel by viewModel<BaseAllAlbumsScreenViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                // need for fullscreen
                WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

                AlbumsScreen(viewModel)


            }
        }
    }

    @Composable
    private fun AlbumsScreen(viewModel: BaseAllAlbumsScreenViewModel) {

        val albumsState = viewModel.albums.collectAsState().value


        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    when (albumsState) {
                        is AlbumsUiState.Loading -> {
                            MyLoader()
                        }
                        is AlbumsUiState.Loaded -> {

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(horizontal = 24.dp)
                            ) {

                                //костыль
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(100.dp)
                                    )
                                }
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(100.dp)
                                    )
                                }

                                albumsState.albumsUiModel.feed.results.forEach {
                                    item {
                                        AlbumCard(it)
                                    }
                                }

                                //костыль
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(24.dp)
                                    )
                                }

                            }
                        }

                        is AlbumsUiState.Error -> Toast.makeText(
                            requireContext(),
                            "adcsdcd",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                //костыль
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(480.dp)
                        .alpha(0.72f)
                        .blur(100.dp)
                        .background(Color.White)
                        .blur(100.dp)
                )
            }
        }
    }
}