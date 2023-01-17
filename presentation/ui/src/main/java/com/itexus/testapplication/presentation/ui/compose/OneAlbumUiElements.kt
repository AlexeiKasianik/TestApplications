package com.itexus.testapplication.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.itexus.testapplication.presentation.ui.R
import com.itexus.testapplication.presentation.ui.models.onAlbumScreen.Genre
import com.itexus.testapplication.presentation.ui.screens.oneAlbumScreen.BaseOneAlbumScreenViewModel
import com.itexus.testapplications.uiKit.DATE_FORMAT
import com.itexus.testapplications.uiKit.formatDate
import com.itexus.testapplications.uiKit.toImproveBigPictureQuality

@Composable
fun OneAlbumScreen(
    viewModel: BaseOneAlbumScreenViewModel,
) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
    ) {

        if (state.detailedAlbumInfo != null) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.detailedAlbumInfo.artworkUrl.toImproveBigPictureQuality())
                        .crossfade(500)
                        .placeholder(R.drawable.placeholder)
                        .build(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                ActionBack(viewModel = viewModel)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(
                    text = state.detailedAlbumInfo.albumName,
                    fontSize = 18.sp,
                    color = grayColor,
                    maxLines = 2,
                    letterSpacing = (-0.04).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_medium))),
                )

                Text(
                    text = state.detailedAlbumInfo.artistName,
                    fontSize = 34.sp,
                    color = black,
                    maxLines = 2,
                    letterSpacing = (-0.04).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_bold))),
                )

                ChipLayout(state.detailedAlbumInfo.genres)

                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.released_description) +
                                state.detailedAlbumInfo.releaseDate.formatDate(DATE_FORMAT),
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_medium))),
                        color = grayColor,
                        fontSize = 12.sp
                    )
                    Text(
                        text = state.copyright,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_medium))),
                        color = grayColor,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        text = stringResource(id = R.string.open_album_action),
                        url = state.detailedAlbumInfo.albumUrl
                    )

                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}

@Composable
fun Button(
    text: String,
    url: String
) {
    val urlHandler = LocalUriHandler.current
    Row(
        modifier = Modifier
            .background(
                color = blue,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = { urlHandler.openUri(url) },
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    color = grayColor
                )
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            text = text,
            style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_semi_bold))),
            color = white,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ActionBack(viewModel: BaseOneAlbumScreenViewModel) {
    Box(Modifier.padding(top = 72.dp, start = 24.dp)) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(white)
                .clickable(
                    onClick = {
                        viewModel.actionBack()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        color = grayColor
                    )
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow_left),
                modifier = Modifier
                    .height(19.dp)
                    .align(Alignment.Center),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ChipLayout(chips: List<Genre>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(top = 4.dp)
    ) {
        chips.forEach {
            Box(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = blue,
                    shape = RoundedCornerShape(12.dp)
                ),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.inter_semi_bold))),
                    color = blue,
                    letterSpacing = (-0.04).sp,
                    fontSize = 12.sp,
                    text = it.name,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            darkIcons = false,
            isNavigationBarContrastEnforced = false,
            color = Color.Transparent
        )
    }
}
