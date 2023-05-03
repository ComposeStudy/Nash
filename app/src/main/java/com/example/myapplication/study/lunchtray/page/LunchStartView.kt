package com.example.myapplication.study.lunchtray.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel

@Composable
fun LunchStartView(lunchTrayViewModel: LunchTrayViewModel, startClick: () -> Unit) {
    Box {
        Button(
            onClick = startClick,
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 60.dp)
                .align(
                    Alignment.Center
                )
        ) {
            Text(text = "start order")
        }
    }
}