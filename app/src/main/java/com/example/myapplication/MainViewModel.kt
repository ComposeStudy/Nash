package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.study.artspace.presenter.ArtSpaceViewModel
import com.example.myapplication.study.cupcake.presenter.CupcakeViewModel
import com.example.myapplication.study.daysofwellness.presenter.DaysOfWellnessViewModel
import com.example.myapplication.study.gridlist.presenter.GridListViewModel
import com.example.myapplication.study.lunchtray.presenter.LunchTrayViewModel
import com.example.myapplication.study.sports.SportScreenView
import com.example.myapplication.study.sports.presenter.SportsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
//    lateinit var cupcakeViewModel: CupcakeViewModel
    val sportsViewModel by lazy { SportsViewModel() }
    val lunchTrayViewModel by lazy { LunchTrayViewModel() }
    val cupcakeViewModel by lazy { CupcakeViewModel() }
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
                is DetailPage.SportsPage -> {
                    _selectPage.emit(DetailPage.SportsPage)
                }
                is DetailPage.LunchTrayPage -> {
                    _selectPage.emit(DetailPage.LunchTrayPage)
                }
                is DetailPage.CupcakePage -> {
                    _selectPage.emit(DetailPage.CupcakePage)
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
                is DetailPage.MainPage -> {
                    _selectPage.emit(DetailPage.MainPage)
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
    object SportsPage : DetailPage
    object LunchTrayPage : DetailPage
    object CupcakePage : DetailPage
    object ArtSpacePage : DetailPage
    object GridListPage : DetailPage
    object DaysListPage : DetailPage
    object MainPage : DetailPage
}