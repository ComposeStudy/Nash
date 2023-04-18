package com.example.myapplication.study.glidlist.presenter

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import kotlinx.coroutines.flow.MutableStateFlow

class affirmationViewModel: ViewModel() {
    val _affrimationState = MutableStateFlow(AffirmationState.Loading)
    fun getAffirmatinoList() {
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
    }



}

sealed interface AffirmationState {
    object Loading: AffirmationState
    data class Show(val items: Array<Topic>): AffirmationState
}
data class Topic(val imageId: Int, val size: Int, val stringId: Int)