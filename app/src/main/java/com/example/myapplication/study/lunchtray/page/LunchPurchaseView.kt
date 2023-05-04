package com.example.myapplication.study.lunchtray.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.study.lunchtray.page.common.CommonLunchTrayFooter
import com.example.myapplication.study.lunchtray.presenter.LunchTrayData
import com.example.myapplication.study.lunchtray.presenter.LunchTrayMenuItem
import com.example.myapplication.study.lunchtray.presenter.LunchTrayRoute
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel

@Composable
fun LunchPurchaseView(lunchTrayViewModel: LunchTrayViewModel, clickCancelBtn: () -> Unit, clickNextBtn: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "purchase")
        LunchPurchaseContent(lunchTrayViewModel.getSelectedMenu())
        CommonLunchTrayFooter(
            clickCancelBtn = clickCancelBtn,
            clickNextBtn = clickNextBtn
        )
    }

}


@Composable
fun LunchPurchaseContent(purchaseData: LunchTrayData) {
    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Text(text = "Order Summary", style = MaterialTheme.typography.bodyLarge)
        Spacer(
            modifier = Modifier.height(5.dp)
        )

        // menu
        if (purchaseData.selectMain.menu.isNotEmpty()) LunchPurchaseItem(menuItem = purchaseData.selectMain)
        if (purchaseData.selectSide.menu.isNotEmpty()) LunchPurchaseItem(menuItem = purchaseData.selectSide)
        if (purchaseData.selectDrink.menu.isNotEmpty()) LunchPurchaseItem(menuItem = purchaseData.selectDrink)

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .background(color = Color.Black)
        )

        // total
        LunchPurchaseSum(purchaseData)
    }
}

@Composable
fun LunchPurchaseSum(purchaseData: LunchTrayData) {
    val textStyle = MaterialTheme.typography.bodyMedium
    val sum = purchaseData.selectMain.price + purchaseData.selectSide.price + purchaseData.selectDrink.price
    val tax = sum * 0.01
    val total = sum + tax
    Text(text = "SubTotal: ${purchaseData.selectMain.currency}$sum", style = textStyle)
    Text(text = "Tax: ${purchaseData.selectMain.currency}$tax", style = textStyle)
    Text(text = "Total: ${purchaseData.selectMain.currency}$total", fontWeight = FontWeight.Bold, style = textStyle)
}

@Composable
fun LunchPurchaseItem(menuItem: LunchTrayMenuItem) {
    Row(modifier = Modifier.height(50.dp)) {
        Text(
            text = menuItem.menu,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${menuItem.currency} ${menuItem.price}",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            style = MaterialTheme.typography.bodySmall
        )
    }
}