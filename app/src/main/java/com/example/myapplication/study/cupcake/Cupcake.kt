package com.example.myapplication.study.cupcake

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
//import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.study.cupcake.presenter.*
import com.example.myapplication.study.cupcake.theme.CupcakeTheme
import com.example.myapplication.study.cupcake.theme.Pink80

@Composable
fun CupcakeMainView(cupcakeViewModel: CupcakeViewModel, backPress: () -> Unit) {
    val backHandlingEnable by remember { mutableStateOf(true) }
    BackHandler(backHandlingEnable, onBack = backPress)

    Scaffold(topBar = {
        CupcakeTopBar()
    }) { paddingValue ->
        CupcakeTheme {
            CupcakeNav(cupcakeViewModel, contentPadding = paddingValue)
        }
    }
}

@Composable
fun CupcakeTopBar() {
    Text(
        text = "Cupcake",
        modifier = Modifier
            .background(color = Pink80)
            .fillMaxWidth()
            .height(50.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun CupcakeNav(cupcakeViewModel: CupcakeViewModel, contentPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CupcakeRoute.Start.name,
        modifier = Modifier.padding(contentPadding)
    ) {
        CupcakeRoute.values().forEach { route ->
            composable(route = route.name) {
                CommonComposableScreen(
                    navController = navController,
                    route = route.name,
                    cupcakeViewModel = cupcakeViewModel
                )
            }
        }
    }
}

@Composable
fun CommonButtomButton(route: String, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedButton(
            enabled = route != CupcakeRoute.Start.name,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White),
            border = BorderStroke(1.dp, Pink80),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                navController.popBackStack(
                    route = CupcakeRoute.Start.name,
                    inclusive = true
                )
            }) {
            Text(text = "Cancel")
        }
        OutlinedButton(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White),
            border = BorderStroke(1.dp, Pink80),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                when (route) {
                    CupcakeRoute.Start.name -> navController.navigate(CupcakeRoute.Flavor.name)
                    CupcakeRoute.Flavor.name -> navController.navigate(CupcakeRoute.Pickup.name)
                    CupcakeRoute.Pickup.name -> navController.navigate(CupcakeRoute.Summary.name)
                    CupcakeRoute.Summary.name -> navController.popBackStack(
                        route = CupcakeRoute.Start.name,
                        inclusive = true
                    )
                }
            }) {
            when (route) {
                CupcakeRoute.Summary.name -> Text(text = "home")
                else -> Text(text = "next")
            }
        }
    }
}

@Composable
fun CommonComposableScreen(
    navController: NavController,
    route: String,
    cupcakeViewModel: CupcakeViewModel
) {
//    val state = cupcakeViewModel.cupcakeState.collectAsState()
    val list = cupcakeViewModel.getList(route)
    Column {

        Spacer(modifier = Modifier.height(30.dp))
        CommonButtomButton(route = route, navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        CommonContent(route = route, radioOptions = list, cupcakeViewModel = cupcakeViewModel)
    }
}

@Composable
fun CommonContent(route: String, radioOptions: List<String>, cupcakeViewModel: CupcakeViewModel) {
    when (route) {
        CupcakeRoute.Summary.name -> SummaryScreen(summary = cupcakeViewModel.getSummary())
        else -> {
            RadioButtonList(
                radioOptions = radioOptions,
            ) { selectedOption ->
                cupcakeViewModel.selectOption(route = route, selectedOption = selectedOption)
            }
        }
    }
}

@Composable
fun RadioButtonList(
    radioOptions: List<String>,
    onOptionSelected: (text: String) -> Unit
) {
    val selectedOption = remember { mutableStateOf(radioOptions[0]) }
    // todo
//    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption.value),
                        onClick = {
                            selectedOption.value = text
                            onOptionSelected(text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption.value),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SummaryScreen(summary: String) {
    Text(text = summary)
}

