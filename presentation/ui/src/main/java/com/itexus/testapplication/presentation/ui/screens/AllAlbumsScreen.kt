package com.itexus.testapplication.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
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

                val scaffoldState = rememberScaffoldState()
                // need for fullscreen
                SideEffect {
                    WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
                }

                Scaffold(scaffoldState = scaffoldState) {
                    AlbumsScreen(viewModel, Modifier.padding(it), scaffoldState.snackbarHostState)
                }
            }
        }
    }

    @Composable
    private fun AlbumsScreen(
        viewModel: BaseAllAlbumsScreenViewModel,
        modifier: Modifier,
        snackbarHostState: SnackbarHostState
    ) {

        val state = viewModel.state.collectAsState().value

        Surface(
            modifier = modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    if (state.isLoaderVisible) MyLoader()

                    if (state.allAlbums != null) {
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

                            state.allAlbums.results.forEach {
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

                    if (state.error != null) {
                        LaunchedEffect(key1 = Unit, block = {
                            snackbarHostState.showSnackbar(state.error)
                        })
                    }
                }

                //костыль
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(480.dp)
                        .alpha(0.85f)
                        .background(Color.White)
                )
            }
        }
    }
}