package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.study.gridlist.GridMainView
import com.example.myapplication.study.artspace.ArtSpaceMainView
import com.example.myapplication.study.daysofwellness.DaysMain


@Composable
fun MainView(
    mainViewModel: MainViewModel
) {
    val selectedPage = mainViewModel.uiState.collectAsState().value
    Log.e("samohao", "MainView state = $selectedPage")
    when (selectedPage) {
        is DetailPage.MainPage -> {
            MainPage(mainViewModel)
        }
        is DetailPage.ArtSpacePage -> {
            ArtSpaceMainView(mainViewModel.artViewModel)
        }
        is DetailPage.GridListPage -> {
            GridMainView(mainViewModel.gridListViewModel) {
                Log.e("samohao", "MainView backpress")
                mainViewModel.goMainPage()
            }
        }
        is DetailPage.DaysListPage -> {
            DaysMain(mainViewModel.daysOfWellnessViewModel) {
                Log.e("samohao", "MainView backpress")
                mainViewModel.goMainPage()
            }
        }
    }
}

@Composable
fun MainPage(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextMenu("30 Days Of Wellness", clickMenu = { mainViewModel.selectPage(DetailPage.DaysListPage) })
        Spacer(modifier = Modifier.height(20.dp))
        TextMenu("Glid List", clickMenu = { mainViewModel.selectPage(DetailPage.GridListPage) })
        Spacer(modifier = Modifier.height(20.dp))
        TextMenu("Art Space", clickMenu = { mainViewModel.selectPage(DetailPage.ArtSpacePage) })
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun TextMenu(menu: String, clickMenu: () -> Unit) {
    Text(
        text = menu, fontSize = 20.sp, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Green)
            .clickable(onClick = clickMenu),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}