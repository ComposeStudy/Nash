package com.example.myapplication.study.daysofwellness

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.study.daysofwellness.presenter.DaysItem
import com.example.myapplication.study.daysofwellness.presenter.DaysOfWellnessViewModel
import com.example.myapplication.study.daysofwellness.theme.DaysOfWellnessTheme
import com.example.myapplication.study.daysofwellness.theme.Typography

@Composable
fun DaysMain(daysViewModel: DaysOfWellnessViewModel, backPressed: () -> Unit) {
    DaysOfWellnessTheme {
        val backHandlingEnable by remember { mutableStateOf(true) }
        BackHandler(backHandlingEnable, onBack = backPressed)

        Scaffold(topBar = {
            DaysTopAppBar()
        }) { padding ->
//            it = PaddingValues(20.dp)
            DaysContent(padding, daysViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysTopAppBar() {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            text = "30 Days Of Wellness",
            style = Typography.displayLarge
        )
    })
}

@Composable
fun DaysContent(padding: PaddingValues, daysViewModel: DaysOfWellnessViewModel) {
    LazyColumn(contentPadding = padding) {
        items(daysViewModel.getDays()) { daysItem ->
            DaysItemView(daysItem = daysItem)
        }
    }
}

@Composable
fun DaysItemView(daysItem: DaysItem) {
    Column(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        .fillMaxWidth()) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = daysItem.imageId),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Text(modifier = Modifier.fillMaxWidth(), text = stringResource(id = daysItem.textId))
    }
}