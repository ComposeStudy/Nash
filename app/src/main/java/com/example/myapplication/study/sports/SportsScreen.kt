package com.example.myapplication.study.sports

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.study.sports.presenter.SportsViewModel
import com.example.myapplication.study.sports.theme.SportsTheme

@Composable
fun SportScreenView(
    sportsViewModel: SportsViewModel,
    windowSize: WindowSizeClass,
    backPress: () -> Unit
) {
    val backHandlingEnable by remember { mutableStateOf(false) }
    BackHandler(backHandlingEnable, onBack = backPress)

    Scaffold(topBar = {
        Text(text = "Sports")
    }) { padding ->
        SportsTheme {
            SportsMain(windowSize, padding)
        }
    }
}

@Composable
fun SportsMain(
    windowSize: WindowSizeClass,
    padding: PaddingValues
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            SportsList()
        }
        WindowWidthSizeClass.Medium -> {
            SportsList()
        }
        WindowWidthSizeClass.Expanded -> {
            SportsListAndDetail(padding)
        }
    }
}

@Composable
fun SportsList(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().background(color = Color.Red)) {
        Text(text = "List")
    }
}
@Composable
fun SportsDetail(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().background(color = Color.Black)) {
        Text(text = "Detail")
    }
}

@Composable
fun SportsListAndDetail(padding: PaddingValues) {
    Row {
        val weight = Modifier
            .weight(1f)
            .padding(padding)
        SportsList(weight)
        SportsDetail(weight)
    }
}