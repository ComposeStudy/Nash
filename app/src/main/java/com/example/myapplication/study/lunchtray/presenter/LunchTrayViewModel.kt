package com.example.myapplication.study.lunchtray.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LunchTrayViewModel: ViewModel() {
    private val _lunchTrayState = MutableStateFlow(LunchTrayData())
    val lunchTrayState = _lunchTrayState.stateIn(
        scope = viewModelScope,
        initialValue = LunchTrayData(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun getMainList(): List<String> {

        return listOf()
    }

    fun getMainList(): List<String> {

        return listOf()
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
    val selectMain: LunchTrayMenuData = LunchTrayMenuData(),
    val selectSide: LunchTrayMenuData = LunchTrayMenuData(),
    val selectDrink: LunchTrayMenuData = LunchTrayMenuData()
)

data class LunchTrayMenuData(
    val Menu: String = "",
    val Desc: String = ""
)