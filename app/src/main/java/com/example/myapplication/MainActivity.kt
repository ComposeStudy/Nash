package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.study.artspace.presenter.ArtSpaceViewModel
import com.example.myapplication.study.cupcake.presenter.CupcakeViewModel
import com.example.myapplication.study.daysofwellness.presenter.DaysOfWellnessViewModel
import com.example.myapplication.study.gridlist.presenter.GridListViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val artViewModel: ArtSpaceViewModel by viewModels()
    private val gridListViewModel: GridListViewModel by viewModels()
    private val daysOfWellnessViewModel: DaysOfWellnessViewModel by viewModels()
    private val cupcakeViewModel: CupcakeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
//        var uiState: ArtSpaceUiState by mutableStateOf(Loading)

//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                artViewModel.uiState
//                    .onEach {
//                        Log.e("samohao" , "onCreate state $it")
//                        uiState = it
//                        when (it) {
//                            is Loading -> { delay(3000) }
//                            else -> {}
//                        }
//                    }
//                    .collect()
//            }
//        }

        setContent {
            DefaultPreview()
        }
    }

    private fun initViewModel() {
        mainViewModel.artViewModel = artViewModel
        mainViewModel.gridListViewModel = gridListViewModel
        mainViewModel.daysOfWellnessViewModel = daysOfWellnessViewModel
//        mainViewModel.cupcakeViewModel = cupcakeViewModel
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainView(mainViewModel = mainViewModel)
    }
}
