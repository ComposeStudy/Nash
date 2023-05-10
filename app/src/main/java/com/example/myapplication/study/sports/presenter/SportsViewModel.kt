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
    private val _sportState = MutableStateFlow<StateSport>(StateSport.Start)
    val stateSport = _sportState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = StateSport.Start
    )
    fun getSportList() = listSport

    fun eventHandler(event: EventSport) {
        viewModelScope.launch {
            when (event) {
                is EventSport.ClickSport -> {
                    _sportState.emit(StateSport.Detail(item = event.item))
                }
                is EventSport.ClickNavBack -> {

                }
            }
        }
    }
}

sealed interface EventSport {
    data class ClickSport(val item: SportItem): EventSport
    object ClickNavBack: EventSport
}

sealed interface StateSport {
    object Start: StateSport

    object List: StateSport

    data class Detail(val item: SportItem = SportItem()): StateSport
}

val listSport = listOf(
    SportItem(
        R.drawable.art_image1,
        name = "baseball",
        desc = "Here is some Baseball news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        R.drawable.art_image2,
        name = "Badminton",
        desc = "Here is some Badminton news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        R.drawable.art_image3,
        name = "basketball",
        desc = "Here is some basketball news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        R.drawable.art_image4,
        name = "bowling",
        desc = "Here is some bowling news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        R.drawable.art_image5,
        name = "cycling",
        desc = "Here is some cycling news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
    SportItem(
        R.drawable.art_image6,
        name = "deadlift",
        desc = "Here is some Baseball news!",
        "padding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = paddingpadding = padding"
    ),
)


data class SportItem(
    val icon: Int = -1,
    val name: String = "",
    val desc: String = "",
    val detail: String = ""
)