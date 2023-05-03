package com.example.myapplication.study.lunchtray

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.study.lunchtray.page.*
import com.example.myapplication.study.lunchtray.presenter.LunchTrayRoute
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel
import com.example.myapplication.study.lunchtray.theme.LunchTrayTheme

@Composable
fun LunchTrayMain(lunchTrayViewModel: LunchTrayViewModel) {
    Scaffold(topBar = {
        LunchTrayTopBar()
    }) { padding ->
        LunchTrayTheme {
            LunchTrayMainView(lunchTrayViewModel, padding)
        }

    }
}

@Composable
fun LunchTrayTopBar() {
    Text(text = "Lunch Tray")
}

@Composable
fun LunchTrayMainView(lunchTrayViewModel: LunchTrayViewModel, contentPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LunchTrayRoute.Start.name,
        modifier = Modifier.padding(contentPadding)
    ) {
        composable(route = LunchTrayRoute.Start.name) { LunchStartView(navController, lunchTrayViewModel) }
        composable(route = LunchTrayRoute.Main.name) { LunchMainView(navController, lunchTrayViewModel) }
        composable(route = LunchTrayRoute.Side.name) { LunchSideView(navController, lunchTrayViewModel) }
        composable(route = LunchTrayRoute.Drink.name) { LunchDrinkView(navController, lunchTrayViewModel) }
        composable(route = LunchTrayRoute.Purchase.name) { LunchPurchaseView(navController, lunchTrayViewModel) }
    }
}