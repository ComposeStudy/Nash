package com.example.myapplication.study.lunchtray

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun LunchTrayMain(lunchTrayViewModel: LunchTrayViewModel, backPress: () -> Unit) {

    val backHandlingEnable by remember { mutableStateOf(true) }
    BackHandler(backHandlingEnable, onBack = backPress)

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
        composable(route = LunchTrayRoute.Start.name) {
            LunchStartView(
                lunchTrayViewModel,
                startClick = {
                    navController.navigate(LunchTrayRoute.Main.name)
                }
            )
        }
        composable(route = LunchTrayRoute.Main.name) {
            LunchMainView(
                lunchTrayViewModel,
                clickCancelBtn = {
                    navController.navigate(LunchTrayRoute.Start.name)
                },
                clickNextBtn = {
                    navController.navigate(LunchTrayRoute.Side.name)
                }
            )
        }
        composable(route = LunchTrayRoute.Side.name) {
            LunchSideView(
                lunchTrayViewModel,
                clickCancelBtn = {
                    navController.navigate(LunchTrayRoute.Main.name)
                },
                clickNextBtn = {
                    navController.navigate(LunchTrayRoute.Drink.name)
                }
            )
        }
        composable(route = LunchTrayRoute.Drink.name) {
            LunchDrinkView(
                lunchTrayViewModel,
                clickCancelBtn = {
                    navController.navigate(LunchTrayRoute.Side.name)
                },
                clickNextBtn = {
                    navController.navigate(LunchTrayRoute.Purchase.name)
                }
            )
        }
        composable(route = LunchTrayRoute.Purchase.name) {
            LunchPurchaseView(
                lunchTrayViewModel,
                clickCancelBtn = {
                    navController.navigate(LunchTrayRoute.Drink.name)
                },
                clickNextBtn = {
                    navController.popBackStack(route = LunchTrayRoute.Drink.name, inclusive = true)
                }
            )
        }
    }
}