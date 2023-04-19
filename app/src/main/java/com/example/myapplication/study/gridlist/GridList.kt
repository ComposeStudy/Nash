package com.example.myapplication.study.gridlist

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.study.gridlist.presenter.GridListState
import com.example.myapplication.study.gridlist.presenter.GridListViewModel
import com.example.myapplication.study.gridlist.presenter.Topic
import com.example.myapplication.study.gridlist.theme.AffirmationTheme

@Composable
fun GridMainView(gridListViewModel: GridListViewModel, backPress: () -> Unit) {
    AffirmationTheme {
        val backHandlingEnable by remember { mutableStateOf(true) }
        BackHandler(backHandlingEnable, onBack = backPress)

        val state = gridListViewModel.uiState.collectAsState().value
        Log.e("samohao", "GridRow state = $state")
        when (state) {
            is GridListState.Loading -> {
                gridListViewModel.getGridList()
            }
            is GridListState.Show -> {
                val gridItems = state.items
                GridView(gridItems = gridItems)
            }
        }
    }
}

@Composable
fun GridView(gridItems: List<Topic>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, top = 5.dp)
    ) {
        items(gridItems) { item ->
            GridItem(
                imageId = item.imageId,
                stringId = item.stringId,
                count = item.size
            )
        }
    }
}

@Composable
fun GridItem(imageId: Int, stringId: Int, count: Int) {
    Surface(shadowElevation = 8.dp, shape = RoundedCornerShape(8.dp)) {
        Box {
            Image(
                modifier = Modifier
                    .size(68.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(id = imageId),
                contentDescription = "architecture"
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 84.dp, top = 0.dp, end = 16.dp, bottom = 8.dp),
                text = stringResource(id = stringId),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 84.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Icon(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = "icon"
                )

                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
