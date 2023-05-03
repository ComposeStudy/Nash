package com.example.myapplication.study.lunchtray.page.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.myapplication.study.lunchtray.presenter.LunchTrayMenuItem

@Composable
fun CommonLunchTrayMenuList(
    menuList: List<LunchTrayMenuItem>,
    opOptionSelected: (menu: LunchTrayMenuItem) -> Unit
) {
    val lastSelectedMenu = remember { mutableStateOf(LunchTrayMenuItem()) }

    LazyColumn(Modifier.selectableGroup()) {
        items(menuList) { menuItem ->
            CommonLunchTrayMenuItem(menuItem, lastSelectedMenu.value) { selectMenuItem ->
                lastSelectedMenu.value = selectMenuItem
                opOptionSelected(selectMenuItem)
            }
        }
    }

}

@Composable
fun CommonLunchTrayMenuItem(
    menuItem: LunchTrayMenuItem,
    lastSelectedItem: LunchTrayMenuItem,
    menuItemClick: (menuItem: LunchTrayMenuItem) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 20.dp)
        .selectable(
            selected = (menuItem.menu == lastSelectedItem.menu),
            onClick = {
                menuItemClick(menuItem)
            }, role = Role.RadioButton
        )
    ) {
        Text(text = menuItem.menu, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.wrapContentSize()) {
            RadioButton(
                selected = (menuItem.menu == lastSelectedItem.menu),
                onClick = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = menuItem.desc,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}