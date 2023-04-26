package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.study.artspace.presenter.ArtSpaceViewModel
import com.example.myapplication.study.daysofwellness.presenter.DaysOfWellnessViewModel
import com.example.myapplication.study.gridlist.presenter.GridListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    lateinit var artViewModel: ArtSpaceViewModel
    lateinit var gridListViewModel: GridListViewModel
    lateinit var daysOfWellnessViewModel: DaysOfWellnessViewModel

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
                is DetailPage.GridListPage -> {
                    _selectPage.emit(DetailPage.GridListPage)
                }
                is DetailPage.DaysListPage -> {
                    _selectPage.emit(DetailPage.DaysListPage)
                }
            }
        }
    }

    fun goMainPage() {
        selectPage(DetailPage.MainPage)
    }

//    fun setArtViewModel(viewModel: ArtSpaceViewModel) {
//        artViewModel = viewModel
//
//    fun setGridViewModel(viewModel: GridListViewModel) {
//        gridListViewModel = viewModel
//    }
//
//    fun setDaysViewModel(viewModel: DaysOfWellnessViewModel) {
//        daysOfWellnessViewModel = viewModel
//    }
}

sealed interface DetailPage {
    object MainPage : DetailPage
    object ArtSpacePage : DetailPage
    object GridListPage : DetailPage
    object DaysListPage : DetailPage
}