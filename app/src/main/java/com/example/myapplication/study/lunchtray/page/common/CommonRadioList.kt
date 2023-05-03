package com.example.myapplication.study.lunchtray.page.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.study.lunchtray.presenter.LunchTrayMenuData

@Composable
fun CommonRadioList(radioOptions: List<LunchTrayMenuData>, opOptionSelected: (text: String) -> Unit) {
    val selectOption = remember { mutableStateOf("") }

    LazyColumn(Modifier.selectableGroup()) {
        items(radioOptions) { item ->
            CommonRadioItem()
        }
    }

}

@Composable
fun CommonRadioItem() {
    Column {
        Text(text = "title")
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.wrapContentSize()) {
            RadioButton(
                selected = (text == selectedOption.value),
                onClick = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}