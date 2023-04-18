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
import com.example.myapplication.study.glidlist.GridMainView
import com.example.myapplication.study.artspace.ArtSpaceMainView
import com.example.myapplication.study.artspace.presenter.ArtSpaceViewModel


@Composable
fun MainView(mainViewModel: MainViewModel, artSpaceViewModel: ArtSpaceViewModel) {
    val selectedPage = mainViewModel._selectPage.collectAsState().value
    Log.e("samohao", "MainView state = $selectedPage")
    when (selectedPage) {
        is DetailPage.MainPage -> {
            MainPage(mainViewModel)
        }
        is DetailPage.ArtSpacePage -> {
            ArtSpaceMainView(artSpaceViewModel)
        }
        is DetailPage.AffirmationPage -> {
            GridMainView()
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
        TextMenu("Art Space", clickMenu = { mainViewModel.selectPage(DetailPage.ArtSpacePage) })
        Spacer(modifier = Modifier.height(30.dp))
        TextMenu("Glid List", clickMenu = { mainViewModel.selectPage(DetailPage.AffirmationPage) })
        Spacer(modifier = Modifier.height(20.dp))
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