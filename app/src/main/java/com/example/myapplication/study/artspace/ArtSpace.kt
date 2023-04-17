package com.example.myapplication.study.artspace

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.study.artspace.theme.ArtSpaceTheme
import com.example.myapplication.study.artspace.presenter.Art
import com.example.myapplication.study.artspace.presenter.ArtSpaceUiState.ChangeArt
import com.example.myapplication.study.artspace.presenter.ArtSpaceUiState.Loading
import com.example.myapplication.study.artspace.presenter.ArtSpaceViewModel

@Composable
fun ArtSpaceMainView(artSpaceViewModel: ArtSpaceViewModel) {
    val stateArtSrc = artSpaceViewModel._artState.collectAsState().value
    Log.e("samohao", "ArtSpaceMainView state = $stateArtSrc")
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (stateArtSrc) {
                is Loading -> {
                    ArtLoadingView()
                    artSpaceViewModel.getArt()
                }
                is ChangeArt -> ArtView(
                    leftClick = { artSpaceViewModel.eventHandler(left = true) },
                    rightClick = { artSpaceViewModel.eventHandler(left = false) },
                    artData = stateArtSrc.artData
                )
            }
        }
    }
}

@Composable
fun ArtLoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "로오오오딩", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ArtView(leftClick: () -> Unit, rightClick: () -> Unit, artData: Art) {
    val sizeSpacer = 50.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(sizeSpacer)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1.0f, fill = true)
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
                    .background(
                        color = Color.Green,
//                            shape = RectangleShape
                    ),
//                        .shadow(elevation = 5.dp),
                shadowElevation = 5.dp
            ) {
                Image(
                    painter = painterResource(id = artData.picture),
                    contentDescription = "Gallery",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(all = 20.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(sizeSpacer)
        )
        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shadowElevation = 5.dp
        ) {
            Column(Modifier.padding(10.dp)) {
                Text(text = artData.title, fontSize = 25.sp, lineHeight = 25.sp)
                Row {
                    Text(text = artData.artist, fontWeight = FontWeight.Black)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = artData.year)
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(sizeSpacer)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Button(onClick = leftClick, shape = RectangleShape, modifier = Modifier.weight(1f)) {
                Text(text = "Left")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = rightClick,
                shape = RoundedCornerShape(corner = CornerSize(4.dp)),
                modifier = Modifier.weight(1f),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "Right")
            }
        }
    }
}
