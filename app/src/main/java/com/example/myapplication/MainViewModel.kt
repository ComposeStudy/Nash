package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var _selectPage: MutableStateFlow<DetailPage> = MutableStateFlow(DetailPage.MainPage)
    val uiState = _selectPage.stateIn(
        scope = viewModelScope,
        initialValue = DetailPage.MainPage,
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun selectPage(detailPage: DetailPage) {
        viewModelScope.launch {
            when (detailPage) {
                is DetailPage.MainPage -> {
                    _selectPage.emit(DetailPage.MainPage)
                }
                is DetailPage.ArtSpacePage -> {
                    _selectPage.emit(DetailPage.ArtSpacePage)
                }
                is DetailPage.AffirmationPage -> {
                    _selectPage.emit(DetailPage.AffirmationPage)
                }
            }
        }
    }

    fun goMainPage() {
        selectPage(DetailPage.MainPage)
    }
}

sealed interface DetailPage {
    object MainPage : DetailPage
    object ArtSpacePage : DetailPage
    object AffirmationPage : DetailPage
}