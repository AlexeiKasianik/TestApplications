package com.itexus.testapplication.presentation.ui.screens

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.itexus.testapplication.presentation.ui.AlbumsUiState
import com.itexus.testapplication.presentation.ui.R
import com.itexus.testapplication.presentation.ui.models.AlbumsUiModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

            Column {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .width(480.dp)
                        .blur(
                            radiusX = 10.dp,
                            radiusY = 10.dp,
                        )
                )

                when(albumsState) {
                    is AlbumsUiState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is AlbumsUiState.Loaded -> {

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
                        ) {


                            albumsState.albumsUiModel.feed.results.forEach {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(it.artworkUrl),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp)
                                                .clip(RoundedCornerShape(24.dp))
                                        )
                                    }
                                }
                            }
                        }
                    }

                    is AlbumsUiState.Error -> Toast.makeText(requireContext(), "adcsdcd", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}