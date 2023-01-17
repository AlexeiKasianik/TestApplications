package com.itexus.testapplication.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.itexus.testapplication.presentation.ui.models.alAlbumsScreen.AlbumInfo
import com.itexus.testapplication.presentation.ui.models.alAlbumsScreen.FeedUI
import com.itexus.testapplication.presentation.ui.screens.albumsScreen.BaseAllAlbumsScreenViewModel
import com.itexus.testapplications.uiKit.toImproveSmallPictureQuality

@Composable
fun AlbumCard(album: AlbumInfo, onItemClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = rememberRipple(
                    color = grayColor
                ),
                interactionSource = MutableInteractionSource()
            ) { onItemClick(album.id) },
        contentAlignment = Alignment.BottomStart
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(album.artworkUrl.toImproveSmallPictureQuality())
                .crossfade(500)
                .placeholder(R.drawable.placeholder)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.verticalGradient(
                        0.5F to black.copy(alpha = 0f),
                        1F to black.copy(alpha = 0.75f)
                    )
                )
        )

        Column {
            Text(
                text = album.name,
                fontSize = 20.sp,
                color = white,
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
) {

    val screenState = viewModel.state.collectAsState().value
    val lazyListState = rememberLazyGridState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (screenState.isLoaderVisible) Loader()

            if (screenState.allAlbums != null) {
                AlbumLazyGrid(
                    lazyListState,
                    screenState.allAlbums,
                    onItemClick = viewModel::albumClick
                )
                CollapsingTopBar(lazyListState = lazyListState)
            }

            if (screenState.error != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = screenState.error),
                        fontSize = 16.sp,
                        color = grayColor,
                        maxLines = 2,
                        letterSpacing = (-0.04).sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_medium))),
                    )

                    ButtonWithColor(modifier = Modifier.padding(8.dp),
                        onClick = { viewModel.reloadData() })
                }
            }
        }
    }
}

@Composable
fun ButtonWithColor(modifier: Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = darkGray)
    ) {
        Text(text = stringResource(id = R.string.reload_albums_action), color = white)
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
                .background(color = white)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            MeasureUnconstrainedViewWidth(
                viewToMeasure = {
                    Text(
                        letterSpacing = (-1).sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_bold))),
                        text = stringResource(id = topBarLabel),
                        fontSize = topBarTextSize
                    )
                }
            ) { measuredEndWidth, measuredEndHeight ->
                Text(
                    modifier = Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)

                        layout(constraints.maxWidth, constraints.maxHeight) {
                            val xOffsetEnd =
                                (constraints.maxWidth / 2) - (measuredEndWidth / 2)

                            val xOffset =
                                calculateCurrentSize(xOffsetEnd.toFloat(), 0f, fraction)

                            val yOffset = topBarHeightPx - placeable.height - 16.dp.toPx()
                            placeable.placeRelative(xOffset.toInt(), yOffset.toInt())
                        }
                    },
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_bold))),
                    text = stringResource(id = topBarLabel),
                    letterSpacing = (-1).sp,
                    fontSize = topBarTextSize,
                    color = black
                )
            }
        }
    }
}

@Composable
private fun AlbumLazyGrid(
    lazyListState: LazyGridState,
    allAlbums: FeedUI,
    onItemClick: (String) -> Unit
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
                AlbumCard(it, onItemClick = { onItemClick(it) })
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
            color = white
        ) {
            Box(modifier = Modifier.size(256.dp), contentAlignment = Alignment.Center) {
                LottieAnimation(composition, iterations = Int.MAX_VALUE)
            }
        }
    }
}
