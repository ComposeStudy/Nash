package com.example.myapplication.study.lunchtray.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LunchTrayViewModel: ViewModel() {
    private val _lunchTrayState = MutableStateFlow(LunchTrayData())
    val lunchTrayState = _lunchTrayState.stateIn(
        scope = viewModelScope,
        initialValue = LunchTrayData(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun getMenuList(route: LunchTrayRoute): List<LunchTrayMenuItem> = when (route) {
        LunchTrayRoute.Main -> arrMainMenu
        LunchTrayRoute.Side -> arrSideMenu
        LunchTrayRoute.Drink -> arrDrinkMenu
    }

    fun selectMainMenu(menuItem: LunchTrayMenuItem) {
        _lunchTrayState.update { lunchTrayData ->
            lunchTrayData.copy(
                selectMain = menuItem
            )
        }
    }

    fun selectSideMenu(menuItem: LunchTrayMenuItem) {
        _lunchTrayState.update { lunchTrayData ->
            lunchTrayData.copy(
                selectSide = menuItem
            )
        }
    }

    fun selectDrinkMenu(menuItem: LunchTrayMenuItem) {
        _lunchTrayState.update { lunchTrayData ->
            lunchTrayData.copy(
                selectDrink = menuItem
            )
        }
    }
}

enum class LunchTrayRoute {
    Start,
    Main,
    Side,
    Drink,
    Purchase
}

data class LunchTrayData(
    val currentRoute: String = LunchTrayRoute.Start.name,
    val selectMain: LunchTrayMenuItem = LunchTrayMenuItem(),
    val selectSide: LunchTrayMenuItem = LunchTrayMenuItem(),
    val selectDrink: LunchTrayMenuItem = LunchTrayMenuItem()
)

data class LunchTrayMenuItem(
    val menu: String = "",
    val desc: String = ""
)

private val arrMainMenu = listOf(
    LunchTrayMenuItem(menu = "A Main", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "B Main", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "C Main", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "D Main", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "E Main", desc = "Main A Main A Main A Main A")
)

private val arrSideMenu = listOf(
    LunchTrayMenuItem(menu = "A Side", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "B Side", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "C Side", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "D Side", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "E Side", desc = "Main A Main A Main A Main A")
)

private val arrDrinkMenu = listOf(
    LunchTrayMenuItem(menu = "A Drink", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "B Drink", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "C Drink", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "D Drink", desc = "Main A Main A Main A Main A"),
    LunchTrayMenuItem(menu = "E Drink", desc = "Main A Main A Main A Main A")
)