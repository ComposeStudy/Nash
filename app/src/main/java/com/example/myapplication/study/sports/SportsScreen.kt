package com.example.myapplication.study.sports

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
            sportsViewModel.eventHandler(event = EventSport.WindowSizeNormal)
        }
        WindowWidthSizeClass.Expanded -> {
            sportsViewModel.eventHandler(event = EventSport.WindowSizeExpanded)
        }
    }

    Scaffold(topBar = {
        SportTop(sportsViewModel = sportsViewModel, windowSize = windowSize) {
            sportsViewModel.eventHandler(EventSport.ClickNavBack)
        }
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
fun SportTop(sportsViewModel: SportsViewModel, windowSize: WindowSizeClass, clickBack: () -> Unit) {
    val sportState = sportsViewModel.stateSport.collectAsState().value
    var hasBack = false
    val title: String
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            if (sportState.screenState == StateScreen.ListAndDetail) {
                title = "LIST AND DETAIL"
            } else {

                hasBack = true
                title = "DETAIL"
            }
        }
        else -> {
            hasBack = true
            title = if (sportState.screenState == StateScreen.List) "LIST" else "DETAIL"
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Pink80),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        if (hasBack) Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier.clickable(onClick = clickBack))
        Text(text = title)
    }
}

@Composable
fun SportsMain(
    windowSize: WindowSizeClass,
    padding: PaddingValues,
    sportsViewModel: SportsViewModel
) {

    val sportState = sportsViewModel.stateSport.collectAsState().value
    
    when (sportState.screenState) {
        StateScreen.List -> {
            SportsList(padding = padding, listSport = sportState.list, clickItem = { item, index ->
                sportsViewModel.eventHandler(EventSport.ClickSport(item = item, index = index))
            })
        }
        StateScreen.Detail -> {
            SportsDetail(padding = padding, itemSport = sportState.detailItem)
        }
        StateScreen.ListAndDetail -> {
            SportsListAndDetail(padding = padding, listSport = sportState.list, sportsViewModel = sportsViewModel)
        }
    }

//    val navController = rememberNavController()
//    val listSport = sportsViewModel.getSportList()
//    val lastDetailItem = sportsViewModel.getSportItem()
//    when (windowSize.widthSizeClass) {
//        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
//            NavHost(
//                navController = navController,
//                startDestination = SportRoute.LIST.name,
//            ) {
//                composable(SportRoute.LIST.name) {
//                    SportsList(
//                        padding = padding,
//                        listSport = listSport,
//                        clickItem = { item, index ->
//                            sportsViewModel.eventHandler(EventSport.ClickSport(item, index))
//                        })
//                }
//                composable(SportRoute.DETAIL.name) {
//                    SportsDetail(itemSport = lastDetailItem)
//                }
//            }
//        }
//        WindowWidthSizeClass.Expanded -> {
//            SportsListAndDetail(padding, listSport = listSport, sportsViewModel = sportsViewModel)
//        }
//    }
}

@Composable
fun SportsList(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp),
    listSport: List<SportItem>,
    clickItem: (SportItem, Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        itemsIndexed(listSport) { index, sportItem ->
            SportItemView(sportItem = sportItem, index = index, clickItem = clickItem)
        }
    }
}

@Composable
fun SportItemView(sportItem: SportItem, index: Int, clickItem: (SportItem, Int) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable { clickItem(sportItem, index) },
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
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = itemSport.imgDetail),
            contentDescription = ""
        )
        Text(text = itemSport.detail, style = Typography.labelLarge)
    }
}

@Composable
fun SportsListAndDetail(
    padding: PaddingValues,
    listSport: List<SportItem>,
    sportsViewModel: SportsViewModel
) {
//    val clickDetail = sportsViewModel.stateSport.collectAsState().value
    val lastSportItem = sportsViewModel.getSportItem()
    Row {
        val weight = Modifier
            .weight(1f)
            .padding(padding)
        SportsList(modifier = weight, listSport = listSport) { sportItem, index ->
            sportsViewModel.eventHandler(EventSport.ClickSport(sportItem, index))
        }
        SportsDetail(modifier = weight, itemSport = lastSportItem)
    }
}