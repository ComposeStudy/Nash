package com.example.myapplication.study.gridlist.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GridListViewModel: ViewModel() {
    private val _gridListState: MutableStateFlow<GridListState> = MutableStateFlow(GridListState.Loading)
    val uiState = _gridListState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GridListState.Loading
    )
    fun getGridList() {
        viewModelScope.launch {
            val topics = listOf(
                Topic(R.string.architecture, 58, R.drawable.architecture),
                Topic(R.string.crafts, 121, R.drawable.crafts),
                Topic(R.string.business, 78, R.drawable.business),
                Topic(R.string.culinary, 118, R.drawable.culinary),
                Topic(R.string.design, 423, R.drawable.design),
                Topic(R.string.fashion, 92, R.drawable.fashion),
                Topic(R.string.film, 165, R.drawable.film),
                Topic(R.string.gaming, 164, R.drawable.gaming),
                Topic(R.string.drawing, 326, R.drawable.drawing),
                Topic(R.string.lifestyle, 305, R.drawable.lifestyle),
                Topic(R.string.music, 212, R.drawable.music),
                Topic(R.string.painting, 172, R.drawable.painting),
                Topic(R.string.photography, 321, R.drawable.photography),
                Topic(R.string.tech, 118, R.drawable.tech)
            )
            _gridListState.emit(GridListState.Show(topics))
        }
    }
}

sealed interface GridListState {
    object Loading: GridListState
    data class Show(val items: List<Topic>): GridListState
}
data class Topic(val stringId: Int, val size: Int, val imageId: Int)