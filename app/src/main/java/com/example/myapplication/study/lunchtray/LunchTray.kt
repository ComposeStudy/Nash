package com.example.myapplication.study.lunchtray

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.study.cupcake.theme.CupcakeTheme

@Composable
fun LunchTrayMain() {
    Scaffold(topBar = {
        LunchTrayTopBar()
    }) {
        CupcakeTheme {
            LunchTrayMainView()
        }

    }
}

@Composable
fun LunchTrayTopBar() {
    Text(text = "Lunch Tray")
}

@Composable
fun LunchTrayMainView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = )
}