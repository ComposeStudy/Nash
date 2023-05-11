package com.example.myapplication.study.sports.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SportsViewModel : ViewModel() {
    private val _sportState = MutableStateFlow(SportState())
    val stateSport = _sportState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SportState()
    )
    private val _notSelect = -1
    fun getSportList() = listSport
    fun getSportItem(): SportItem {
        val index = _sportState.value.preDetailIndex
        return if (index > listSport.size || index == -1) listSport[0] else listSport[index]
    }

    fun eventHandler(event: EventSport) {
        viewModelScope.launch {
            when (event) {
                is EventSport.ClickSport -> {
                    _sportState.update {
                        it.copy(
                            detailIndex = event.index,
                            preDetailIndex = event.index,
                            detailItem = event.item,
                            screenState = if (it.screenState == StateScreen.List) StateScreen.Detail else it.screenState
                        )
                    }
                }
                is EventSport.ClickNavBack -> {
                    _sportState.update {
                        it.copy(
                            screenState = if (it.screenState == StateScreen.Detail) StateScreen.List else it.screenState
                        )
                    }
                }
                is EventSport.WindowSizeExpanded -> {
                    _sportState.update {
                        it.copy(
                            screenState = if (it.screenState != StateScreen.ListAndDetail) StateScreen.ListAndDetail else it.screenState
                        )
                    }
                }
                is EventSport.WindowSizeNormal -> {
                    _sportState.update {
                        it.copy(
                            screenState = if (it.screenState == StateScreen.ListAndDetail) {
                                if (it.preDetailIndex != _notSelect)
                                    StateScreen.Detail
                                else
                                    StateScreen.List
                            } else {
                                it.screenState
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed interface EventSport {
    data class ClickSport(val item: SportItem, val index: Int): EventSport
    object ClickNavBack: EventSport
    object WindowSizeExpanded: EventSport
    object WindowSizeNormal: EventSport
}

sealed interface StateScreen {

    object List: StateScreen

    object Detail: StateScreen

    object ListAndDetail: StateScreen
}

data class SportState(
    val screenState: StateScreen = StateScreen.List,
    val list: List<SportItem> = listSport,
    val detailItem: SportItem = SportItem(),
    val detailIndex: Int = -1,
    val preDetailIndex: Int = -1
)

enum class SportRoute {
    LIST,
    DETAIL
}


data class SportItem(
    val imgList: Int = -1,
    val imgDetail: Int = -1,
    val name: String = "",
    val desc: String = "",
    val detail: String = ""
)

val listSport = listOf(
    SportItem(
        imgList = R.drawable.ic_baseball_square,
        imgDetail = R.drawable.ic_baseball_banner,
        name = "baseball",
        desc = "Here is some Baseball news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        imgList = R.drawable.ic_badminton_square,
        imgDetail = R.drawable.ic_badminton_banner,
        name = "Badminton",
        desc = "Here is some Badminton news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        imgList = R.drawable.ic_basketball_square,
        imgDetail = R.drawable.ic_basketball_banner,
        name = "basketball",
        desc = "Here is some basketball news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        imgList = R.drawable.ic_bowling_square,
        imgDetail = R.drawable.ic_bowling_banner,
        name = "bowling",
        desc = "Here is some bowling news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        imgList = R.drawable.ic_cycling_square,
        imgDetail = R.drawable.ic_cycling_banner,
        name = "cycling",
        desc = "Here is some cycling news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        imgList = R.drawable.ic_golf_square,
        imgDetail = R.drawable.ic_golf_banner,
        name = "golf",
        desc = "Here is some golf news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
)