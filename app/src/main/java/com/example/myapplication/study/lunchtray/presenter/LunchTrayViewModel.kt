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

    fun getSelectedMenu() = _lunchTrayState.value

    fun getMenuList(route: LunchTrayRoute): List<LunchTrayMenuItem> = when (route) {
        LunchTrayRoute.Main -> arrMainMenu
        LunchTrayRoute.Side -> arrSideMenu
        LunchTrayRoute.Drink -> arrDrinkMenu
        else -> { listOf() }
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
    val desc: String = "",
    val price: Int = 0,
    val currency: String = ""
)

private val arrMainMenu = listOf(
    LunchTrayMenuItem(menu = "A Main", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "B Main", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "C Main", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "D Main", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "E Main", desc = "Main A Main A Main A Main A", price = 5, currency = "$")
)

private val arrSideMenu = listOf(
    LunchTrayMenuItem(menu = "A Side", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "B Side", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "C Side", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "D Side", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "E Side", desc = "Main A Main A Main A Main A", price = 5, currency = "$")
)

private val arrDrinkMenu = listOf(
    LunchTrayMenuItem(menu = "A Drink", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "B Drink", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "C Drink", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "D Drink", desc = "Main A Main A Main A Main A", price = 5, currency = "$"),
    LunchTrayMenuItem(menu = "E Drink", desc = "Main A Main A Main A Main A", price = 5, currency = "$")
)