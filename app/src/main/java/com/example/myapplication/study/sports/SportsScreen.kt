package com.example.myapplication.study.sports

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.study.sports.presenter.*
import com.example.myapplication.study.sports.theme.Pink80
import com.example.myapplication.study.sports.theme.SportsTheme
import com.example.myapplication.study.sports.theme.Typography

@Composable
fun SportScreenView(
    sportsViewModel: SportsViewModel,
    windowSize: WindowSizeClass,
    backPress: () -> Unit
) {
    val backHandlingEnable by remember { mutableStateOf(false) }
    BackHandler(backHandlingEnable, onBack = backPress)

    val uistate = sportsViewModel.stateSport.collectAsState().value

    Scaffold(topBar = {
        SportTop(uistate = uistate, windowSize = windowSize)
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
fun SportTop(uistate: SportStateData, windowSize: WindowSizeClass) {
    var hasBack = false
    var title = "LIST"
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            hasBack = true
            title = if (uistate.state == StateSport.ListAndDetail) "LIST AND DETAIL" else "DETAIL"
        }
        else -> {
            hasBack = true
            title = if (uistate.state == StateSport.List) "LIST" else "DETAIL"
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
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

    val navController = rememberNavController()
    val listSport = sportsViewModel.getSportList()
    val eventState = sportsViewModel.stateSport.collectAsState().value
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
            NavHost(
                navController = navController,
                startDestination = SportRoute.LIST.name,
            ) {
                composable(SportRoute.LIST.name) {
                    SportsList(padding = padding, listSport = listSport)
                }
                composable(SportRoute.DETAIL.name) {
                    SportsDetail(itemSport = eventState.)
                }
            }
        }
        WindowWidthSizeClass.Expanded -> {
            SportsListAndDetail(padding, listSport = listSport)
        }
    }
}

@Composable
fun SportsList(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp),
    listSport: List<SportItem>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        items(listSport) { sportItem ->
            SportItemView(sportItem = sportItem)
        }
    }
}

@Composable
fun SportItemView(sportItem: SportItem, clickItem: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable(onClick = clickItem),
        shadowElevation = 5.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.background(color = Color.White)
        ) {
            Image(
                painter = painterResource(id = sportItem.imgList),
                modifier = Modifier.size(150.dp),
                contentDescription = "baseball",
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = sportItem.name, style = Typography.titleLarge)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "News", style = Typography.labelLarge)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = sportItem.desc, style = Typography.bodyMedium)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun SportsDetail(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp),
    itemSport: SportItem
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(padding)
    ) {
        Text(text = "Detail")
    }
}

@Composable
fun SportsListAndDetail(padding: PaddingValues, listSport: List<SportItem>, sportsViewModel: SportsViewModel) {
    val clickDetail = sportsViewModel.stateSport.collectAsState().value
    when (clickDetail) {
        is StateSport.List -> {

        }
        is StateSport.Detail -> {

        }
    }
    Row {
        val weight = Modifier
            .weight(1f)
            .padding(padding)
        SportsList(weight, listSport = listSport)
        SportsDetail(weight, listSport = listSport)
    }
}