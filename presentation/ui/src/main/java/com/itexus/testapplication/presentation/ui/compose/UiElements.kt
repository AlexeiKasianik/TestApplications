package com.itexus.testapplication.presentation.ui.compose

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.itexus.testapplication.presentation.ui.models.AlbumInfo

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
                .build(),
            loading = {
                CircularProgressIndicator()
            },
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
        )

        Column {
            Text(
                text = album.name,
                fontSize = 20.sp,
                color = White,
                modifier = Modifier.padding(start = 16.dp, end = 15.dp)
            )

            Text(
                text = album.artistName,
                fontSize = 16.sp,
                color = White,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 4.dp)
            )
        }


    }
}

@Composable
fun MyLoader() {

    val infiniteTransition = rememberInfiniteTransition()

    val twinCircleAnimation by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .size(120.dp)
            .padding(12.dp)
            .clip(CircleShape)
            .background(Red),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(15.dp)
                .scale(twinCircleAnimation)
                .clip(CircleShape)
                .background(White)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Box(
            modifier = Modifier
                .size(15.dp)
                .scale(twinCircleAnimation)
                .clip(CircleShape)
                .background(White)
        )
    }

}


