package com.example.myapplication.study.lunchtray.page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel

@Composable
fun LunchPurchaseView(navController: NavController, lunchTrayViewModel: LunchTrayViewModel) {
    Text(text = "drink")
}