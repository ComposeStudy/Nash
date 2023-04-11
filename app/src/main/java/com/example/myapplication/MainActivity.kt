package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var selectIndex = 0
    private val arrArt = arrayListOf(
        Art(title = "레오는 왜 현술 조 가 되었나", artist = "artist", year = "2007", R.drawable.image1),
        Art(title = "아리아와 아리야는 어떻게 다른가", artist = "artist", year = "2007", R.drawable.image2),
        Art(
            title = "필립 방송할때 친구들 난입해서 부르는 이름은?",
            artist = "artist",
            year = "2007",
            R.drawable.image3
        ),
        Art(title = "강아지의 일생", artist = "artist", year = "2007", R.drawable.image4),
        Art(title = "축구 꿈나무", artist = "artist", year = "2007", R.drawable.image5),
        Art(title = "사이드백은 어떤 형태로 뛰어야 하는가", artist = "artist", year = "2007", R.drawable.image6),
        Art(title = "오늘 하루 행복한가", artist = "artist", year = "2007", R.drawable.image7),
        Art(title = "자존감을 높이는 법", artist = "artist", year = "2007", R.drawable.image8),
        Art(title = "인생은 긴데 난 준비가 되어 있는가", artist = "artist", year = "2007", R.drawable.image9),
        Art(title = "슬퍼지려 하기전에", artist = "artist", year = "2007", R.drawable.image10),
        Art(title = "합리화는 정신건강에 얼마나 도움이 되나", artist = "artist", year = "2007", R.drawable.image11),
        Art(title = "image1", artist = "artist", year = "2007", R.drawable.image12)
    )
    private var stateImageSrc = mutableStateOf(arrArt[0])

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView({ clickLeft() }, { clickRight() }, stateImageSrc)
                }
            }
        }
    }


    @Composable
    fun MainView(leftClick: () -> Unit, rightClick: () -> Unit, stateArtSrc: MutableState<Art>) {
//        var imageSrc = remember { mutableStateOf(R.drawable.image1) }
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
                            color = colorResource(id = android.R.color.holo_green_dark),
//                            shape = RectangleShape
                        ),
//                        .shadow(elevation = 5.dp),
                    shadowElevation = 5.dp
                ) {
                    Image(
                        painter = painterResource(id = stateArtSrc.value.picture),
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
                    Text(text = stateArtSrc.value.title, fontSize = 25.sp, lineHeight = 25.sp)
                    Row {
                        Text(text = stateArtSrc.value.artist, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = stateArtSrc.value.year)
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(sizeSpacer)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                Button(onClick = leftClick, shape = RectangleShape, modifier = Modifier.weight(1f)) {
                    Text(text = "Left")
                }
                Spacer(modifier = Modifier.width(30.dp))
                Button(onClick = rightClick, shape = RoundedCornerShape(corner = CornerSize(4.dp)), modifier = Modifier.weight(1f), elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)) {
                    Text(text = "Right")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
//        MainView()
        }
    }

    private fun clickLeft() {
        lifecycleScope.launch {
            if (selectIndex == 0) selectIndex = 11 else selectIndex--
            stateImageSrc.value = arrArt[selectIndex]
        }
    }

    private fun clickRight() {
        lifecycleScope.launch {
            if (selectIndex == 11) selectIndex = 0 else selectIndex++
            stateImageSrc.value = arrArt[selectIndex]
        }
    }
}

data class Art(
    val title: String,
    val artist: String,
    val year: String,
    val picture: Int
)
