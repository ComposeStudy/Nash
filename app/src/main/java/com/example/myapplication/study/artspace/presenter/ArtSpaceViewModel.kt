package com.example.myapplication.study.artspace.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import kotlinx.coroutines.flow.*
import com.example.myapplication.study.artspace.presenter.ArtSpaceUiState.ChangeArt
import com.example.myapplication.study.artspace.presenter.ArtSpaceUiState.Loading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtSpaceViewModel : ViewModel() {
    var _artState: MutableStateFlow<ArtSpaceUiState> = MutableStateFlow(Loading)
//    val artUistate = _artState.stateIn(
//        scope = viewModelScope,
//        initialValue = Loading,
//        started = SharingStarted.WhileSubscribed(5_000)
//    )
    private var arrArt = arrayListOf<Art>()
    private var selectIndex = 0

    fun eventHandler(left: Boolean) {
        Log.e("samohao" , "eventHandler left $left")
        viewModelScope.launch {
            if (left) {
                if (selectIndex == 0) selectIndex = 11 else selectIndex--
            } else {
                if (selectIndex == 11) selectIndex = 0 else selectIndex++
            }
            _artState.emit(ChangeArt(arrArt[selectIndex]))
        }
    }
    
    fun getArt() {
        viewModelScope.launch {
            delay(3000)
            arrArt = arrayListOf(
                Art(title = "레오는 왜 현술 조 가 되었나", artist = "artist", year = "2007", R.drawable.image1),
                Art(title = "아리아와 아리야는 어떻게 다른가", artist = "artist", year = "2007", R.drawable.image2),
                Art(title = "필립 방송할때 친구들 난입해서 부르는 이름은?",artist = "artist",year = "2007",R.drawable.image3),
                Art(title = "소녀 장사는 어떻게 67kg 데드리프트를 해냈나?", artist = "artist", year = "2007", R.drawable.image4),
                Art(title = "축구 꿈나무", artist = "artist", year = "2007", R.drawable.image5),
                Art(title = "사이드백은 어떤 형태로 뛰어야 하는가", artist = "artist", year = "2007", R.drawable.image6),
                Art(title = "오늘 하루 행복한가", artist = "artist", year = "2007", R.drawable.image7),
                Art(title = "자존감을 높이는 법", artist = "artist", year = "2007", R.drawable.image8),
                Art(title = "인생은 긴데 난 준비가 되어 있는가", artist = "artist", year = "2007", R.drawable.image9),
                Art(title = "슬퍼지려 하기전에", artist = "artist", year = "2007", R.drawable.image10),
                Art(title = "합리화는 정신건강에 얼마나 도움이 되나", artist = "artist", year = "2007", R.drawable.image11),
                Art(title = "image1", artist = "artist", year = "2007", R.drawable.image12)
            )
            _artState.emit(ChangeArt(arrArt[0]))
        }
    }
}

sealed interface ArtSpaceUiState {
    object Loading : ArtSpaceUiState
    data class ChangeArt(val artData: Art) : ArtSpaceUiState
}

data class Art(
    val title: String,
    val artist: String,
    val year: String,
    val picture: Int
)