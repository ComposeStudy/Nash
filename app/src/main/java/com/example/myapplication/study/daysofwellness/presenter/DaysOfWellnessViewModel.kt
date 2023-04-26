package com.example.myapplication.study.daysofwellness.presenter

import androidx.lifecycle.ViewModel
import com.example.myapplication.R

class DaysOfWellnessViewModel: ViewModel() {


    fun getDays(): List<DaysItem> {
        return arrDaysImg
    }
}


val arrDaysImg = listOf(
    DaysItem(R.drawable.days_image1, R.string.days_string_1, R.string.days_string_ext_1),
    DaysItem(R.drawable.days_image2, R.string.days_string_2, R.string.days_string_ext_2),
    DaysItem(R.drawable.days_image3, R.string.days_string_3, R.string.days_string_ext_3),
    DaysItem(R.drawable.days_image4, R.string.days_string_4, R.string.days_string_ext_4),
    DaysItem(R.drawable.days_image5, R.string.days_string_5, R.string.days_string_ext_5),
    DaysItem(R.drawable.days_image6, R.string.days_string_6, R.string.days_string_ext_6),
    DaysItem(R.drawable.days_image7, R.string.days_string_7, R.string.days_string_ext_7),
    DaysItem(R.drawable.days_image8, R.string.days_string_8, R.string.days_string_ext_8),
    DaysItem(R.drawable.days_image9, R.string.days_string_9, R.string.days_string_ext_9),
    DaysItem(R.drawable.days_image10, R.string.days_string_10, R.string.days_string_ext_10),
)

data class DaysItem(
    val imageId: Int,
    val textId: Int,
    val textExtId: Int
)