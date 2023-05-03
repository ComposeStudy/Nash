package com.example.myapplication.study.lunchtray.page.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.study.lunchtray.theme.Purple40

@Composable
fun CommonFooter() {
    Row(
        modifier = Modifier.padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "CANCEL")
        }
        Button(onClick = { }, modifier = Modifier.background(Purple40), shape = RoundedCornerShape(5.dp) ) {
            Text(text = "NEXT", color = Color.White)
        }
    }
}