package com.example.myapplication.study.lunchtray.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.study.lunchtray.page.common.CommonLunchTrayFooter
import com.example.myapplication.study.lunchtray.page.common.CommonLunchTrayMenuList
import com.example.myapplication.study.lunchtray.presenter.LunchTrayRoute
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel

@Composable
fun LunchSideView(navController: NavController, lunchTrayViewModel: LunchTrayViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
//        CommonLunchTrayMenuList(
//            menuList = lunchTrayViewModel.getMenuList(LunchTrayRoute.Main)
//        ) { lunchMainMenu ->
//            lunchTrayViewModel.selectMainMenu(lunchMainMenu)
//        }

        Text(text = "side")
        CommonLunchTrayFooter(
            clickCancelBtn = { navController.popBackStack() },
            clickNextBtn = { navController.navigate(LunchTrayRoute.Drink.name) }
        )
    }
}