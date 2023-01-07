package com.itexus.testapplication.presentation.ui.compose

import android.graphics.Bitmap
import android.util.LruCache
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.itexus.testapplication.presentation.ui.R
import com.itexus.testapplication.presentation.ui.models.AlbumInfo
import com.itexus.testapplication.presentation.ui.models.FeedUI
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.BaseAllAlbumsScreenViewModel
import dev.jakhongirmadaminov.glassmorphiccomposables.fastblur
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@Composable
fun AlbumCard(album: AlbumInfo) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(album.artworkUrl)
                .crossfade(500)
                .placeholder(R.drawable.placeholder)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
        )

        // Костыль из-за которого нужно будет клик вешать на 2 объекта
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.verticalGradient(
                        0.5F to Black.copy(alpha = 0f),
                        1F to Black.copy(alpha = 0.75f)
                    )
                )
        )

        Column {
            Text(
                text = album.name,
                fontSize = 20.sp,
                color = White,
                maxLines = 2,
                letterSpacing = (-0.04).sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_semi_bold))),
                modifier = Modifier.padding(start = 16.dp, end = 15.dp)
            )

            Text(
                text = album.artistName,
                fontSize = 16.sp,
                color = grayColor,
                maxLines = 2,
                letterSpacing = (-0.04).sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_medium))),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
internal fun AlbumsScreen(
    viewModel: BaseAllAlbumsScreenViewModel,
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
) {

    val screenState = viewModel.state.collectAsState().value
    val lazyListState = rememberLazyGridState()

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            lateinit var memoryCache: LruCache<String, Bitmap>
            val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

            val cacheSize = maxMemory / 8

            memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

                override fun sizeOf(key: String, bitmap: Bitmap): Int {
                    // The cache size will be measured in kilobytes rather than
                    // number of items.
                    return bitmap.byteCount / 1024
                }
            }




            var capturedBitmap by remember { mutableStateOf<Bitmap?>(memoryCache["BLURRED_BG_KEY"]) }
            val captureController = rememberCaptureController()

            Capturable(controller = captureController, onCaptured = { bitmap, _ ->
                // This is captured bitmap of a content inside Capturable Composable.
                bitmap?.let {
                    fastblur(it.asAndroidBitmap(), 1f, 50)?.let { fastBlurred ->
                        // Bitmap is captured successfully. Do something with it!
                        memoryCache.put("BLURRED_BG_KEY", fastBlurred)
                        capturedBitmap = fastBlurred
                    }
                }
            }) {



                if (capturedBitmap == null) captureController.capture()




            }

            if (screenState.isLoaderVisible) Loader()

            if (screenState.allAlbums != null) {
                AlbumLazyGrid(lazyListState, screenState.allAlbums)
                CollapsingTopBar(lazyListState = lazyListState)
            }

            if (screenState.error != null) {
                LaunchedEffect(key1 = Unit, block = {
                    snackbarHostState.showSnackbar(screenState.error)
                })
            }
        }
    }
}

@Composable
private fun CollapsingTopBar(
    lazyListState: LazyGridState
) {

    val topBarLabel = R.string.top_bar_title
    var maximumScroll: Float
    var topBarExpandedHeight: Float
    var topBarCollapsedHeight: Float

    var topBarTextSizeExpanded: Float
    var topBarTextSizeCollapsed: Float

    with(LocalDensity.current) {
        maximumScroll = 30.dp.toPx()

        topBarExpandedHeight = 120.dp.toPx()
        topBarCollapsedHeight = 100.dp.toPx()

        topBarTextSizeExpanded = 36.sp.toPx()
        topBarTextSizeCollapsed = 16.sp.toPx()
    }

    val currentOffset: Float by remember {
        derivedStateOf {
            val offset = lazyListState.firstVisibleItemScrollOffset
            if (lazyListState.firstVisibleItemIndex > 0) {
                maximumScroll
            } else {
                offset.toFloat().coerceIn(0f, maximumScroll)
            }
        }
    }

    var topBarHeight: Dp
    var topBarHeightPx: Float
    var topBarTextSize: TextUnit
    var fraction: Float
    with(LocalDensity.current) {
        fraction = getFraction(maximumScroll, currentOffset)
        topBarHeightPx =
            calculateCurrentSize(topBarCollapsedHeight, topBarExpandedHeight, fraction)
        topBarHeight = topBarHeightPx.toDp()
        topBarTextSize =
            calculateCurrentSize(topBarTextSizeCollapsed, topBarTextSizeExpanded, fraction).toSp()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(topBarHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f)
                .background(color = White)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MeasureUnconstrainedViewWidth(
                viewToMeasure = {
                    // Calculating the end text size so we can animate it properly
                    Text(
                        text = stringResource(id = topBarLabel),
                        fontSize = topBarTextSize
                    )
                }
            ) { measuredEndWidth, measuredEndHeight ->
                // Our text view that we are animating based on the returned measured "end" values
                Text(
                    modifier = Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)

                        layout(constraints.maxWidth, constraints.maxHeight) {
                            val xOffsetEnd =
                                (constraints.maxWidth / 2) - (measuredEndWidth / 2)

                            val xOffset =
                                calculateCurrentSize(xOffsetEnd.toFloat(), 0f, fraction)

                            val yOffset = (topBarHeightPx / 2) - (placeable.height / 2)
                            placeable.placeRelative(xOffset.toInt(), yOffset.toInt())
                        }
                    },
                    text = stringResource(id = topBarLabel),
                    fontSize = topBarTextSize,
                    color = Black
                )
            }
        }
    }
}

@Composable
private fun AlbumLazyGrid(
    lazyListState: LazyGridState,
    allAlbums: FeedUI
) {
    LazyVerticalGrid(
        state = lazyListState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 124.dp,
            bottom = 40.dp
        ),
    ) {
        allAlbums.results.forEach {
            item {
                AlbumCard(it)
            }
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader_lottie))
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            Box(modifier = Modifier.size(256.dp), contentAlignment = Alignment.Center) {
                LottieAnimation(composition)
            }
        }
    }
}
