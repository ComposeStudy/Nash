package com.example.myapplication.study.sports

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.study.sports.presenter.SportsViewModel
import com.example.myapplication.study.sports.presenter.StateSport
import com.example.myapplication.study.sports.theme.Pink80
import com.example.myapplication.study.sports.theme.SportsTheme

@Composable
fun SportScreenView(
    sportsViewModel: SportsViewModel,
    windowSize: WindowSizeClass,
    backPress: () -> Unit
) {
    val backHandlingEnable by remember { mutableStateOf(false) }
    BackHandler(backHandlingEnable, onBack = backPress)

    val state = sportsViewModel.stateSport.collectAsState().value

    Scaffold(topBar = {
        SportTop(state = state, windowSize = windowSize)
    }) { padding ->
        SportsTheme {
            SportsMain(
                windowSize = windowSize,
                padding = padding,
                sportsViewModel = sportsViewModel
            )
        }
    }
}

@Composable
fun SportTop(state: StateSport, windowSize: WindowSizeClass) {
    var hasBack = false
    var title = "LIST"
    when (state) {
        is StateSport.Detail -> {
            hasBack = true
            title = "DETAIL"

        }
        else -> {}
    }
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            hasBack = true
            title = "DETAIL"
        }
        else -> {}
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 50.dp)
            .background(color = Pink80),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (hasBack) Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        Text(text = title)
    }
}

@Composable
fun SportsMain(
    windowSize: WindowSizeClass,
    padding: PaddingValues,
    sportsViewModel: SportsViewModel
) {

//    when (state) {
//        is StateSport.Start -> {
//
//        }
//        is StateSport.List -> {
//
//        }
//        is StateSport.Detail -> {
//
//        }
//    }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            SportsList(padding = padding)
        }
        WindowWidthSizeClass.Medium -> {
            SportsList(padding = padding)
        }
        WindowWidthSizeClass.Expanded -> {
            SportsListAndDetail(padding)
        }
    }
}

@Composable
fun SportsList(modifier: Modifier = Modifier, padding: PaddingValues) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Red)
            .padding(padding)
    ) {
        Text(text = "List")
    }
}

@Composable
fun SportsDetail(modifier: Modifier = Modifier, padding: PaddingValues) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
            .padding(padding)
    ) {
        Text(text = "Detail")
    }
}

@Composable
fun SportsListAndDetail(padding: PaddingValues) {
    Row {
        val weight = Modifier
            .weight(1f)
            .padding(padding)
        SportsList(weight, padding = padding)
        SportsDetail(weight, padding = padding)
    }
}