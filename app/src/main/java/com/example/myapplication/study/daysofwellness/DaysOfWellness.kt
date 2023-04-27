package com.example.myapplication.study.daysofwellness

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
                .height(40.dp),
            text = "30 Days Of Wellness",
            style = Typography.titleLarge
        )
    })
}

@Composable
fun DaysContent(padding: PaddingValues, daysViewModel: DaysOfWellnessViewModel) {
    LazyColumn(contentPadding = padding) {
        itemsIndexed(items = daysViewModel.getDays()) { index, daysItem ->
            DaysItemRow(daysItem = daysItem, index)
        }
    }
}

@Composable
fun DaysItemRow(daysItem: DaysItem, index: Int) {
//    Card(
//        modifier = Modifier
//            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//            .fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
//    ) {
//        DaysItemView(daysItem = daysItem)
//    }

    Surface(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth(), shadowElevation = 5.dp
    ) {
        DaysItemView(daysItem = daysItem, index)
    }
}

@Composable
fun DaysItemView(daysItem: DaysItem, index: Int) {
    val clickItem = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            text = "Day ${index + 1}",
            style = Typography.labelLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = daysItem.textId),
            style = Typography.bodyLarge
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { clickItem.value = !clickItem.value },
            painter = painterResource(id = daysItem.imageId),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        AnimatedVisibility(
            visible = clickItem.value, modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = daysItem.textExtId),
                style = Typography.bodyMedium
            )
        }
    }
}