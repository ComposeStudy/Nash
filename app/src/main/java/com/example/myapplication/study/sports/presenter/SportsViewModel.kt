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
    private val _sportState = MutableStateFlow(SportStateData())
    val stateSport = _sportState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SportStateData()
    )
    fun getSportList() = listSport

    fun eventHandler(event: EventSport) {
        viewModelScope.launch {
            when (event) {
                is EventSport.ClickSport -> {
                    _sportState.update {
                        it.copy(
                            detailIndex = event.index,
                            preDetailIndex = it.detailIndex,
                            detailItem = event.item
                        )
                    }
                }
                is EventSport.ClickNavBack -> {

                }
                is EventSport.WindowSizeExpanded -> {
                    _sportState.update {
                        it.copy(
                            detailIndex = event.data.detailIndex,
                            d
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
    data class WindowSizeExpanded(val data: SportStateData): EventSport
    data class WindowSizeNormal(val data: SportStateData): EventSport
}

sealed interface StateSport {

    object List: StateSport

    object Detail: StateSport

    object ListAndDetail: StateSport
}

data class SportStateData(
    val state: StateSport = StateSport.List,
    val listSport: List<SportItem> = listOf(),
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