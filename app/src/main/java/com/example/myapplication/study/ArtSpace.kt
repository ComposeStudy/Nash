package com.example.myapplication.ui

import android.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.study.presenter.Art
import com.example.myapplication.study.presenter.ArtSpaceUiState
import com.example.myapplication.study.presenter.ArtSpaceUiState.ChangeArt
import com.example.myapplication.study.presenter.ArtSpaceUiState.Loading
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ArtView(leftClick: () -> Unit, rightClick: () -> Unit, stateArtSrc: ArtSpaceUiState) {

//    Log.e("smaohao", "ArtView state $stateArtSrc")
    when (stateArtSrc) {
        is Loading -> {
            ArtLoadingView()
        }
//            is Init -> ArtMainView(
//                leftClick = leftClick,
//                rightClick = rightClick,
//                artData = stateArtSrc.artData
//            )
        is ChangeArt -> ArtMainView(
            leftClick = leftClick,
            rightClick = rightClick,
            artData = stateArtSrc.artData
        )
    }
}

@Composable
fun ArtLoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "로오오오딩", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ArtMainView(leftClick: () -> Unit, rightClick: () -> Unit, artData: Art) {
//        var imageSrc = remember { mutableStateOf(R.drawable.image1) }
    val sizeSpacer = 50.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
//            .background(color = Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .height(sizeSpacer)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1.0f, fill = true)
//                    .background(
//                        color = colorResource(id = android.R.color.holo_red_dark),
//                        shape = RectangleShape
//                    )
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
                    .background(
                        color = colorResource(id = R.color.holo_green_dark),
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
