package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.study.presenter.ArtSpaceUiState
import com.example.myapplication.study.presenter.ArtSpaceViewModel
import com.example.myapplication.ui.ArtView
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.study.presenter.ArtSpaceUiState.ChangeArt
import com.example.myapplication.study.presenter.ArtSpaceUiState.Loading
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val artViewModel: ArtSpaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var uiState: ArtSpaceUiState by mutableStateOf(Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                artViewModel.uiState
                    .onEach {
                        Log.e("smaohao" , "onCreate state $it")
                        uiState = it
                        when (it) {
                            is Loading -> { delay(3000) }
                            else -> {}
                        }
                    }
                    .collect()
            }
        }

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtView({ clickLeft() }, { clickRight() }, uiState)
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
        artViewModel.eventHandler(left = true)
    }

    private fun clickRight() {
        artViewModel.eventHandler(left = false)
    }
}
