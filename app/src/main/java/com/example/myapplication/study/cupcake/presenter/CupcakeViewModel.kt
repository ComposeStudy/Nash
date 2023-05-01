package com.example.myapplication.study.cupcake.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CupcakeViewModel : ViewModel() {
    private val _screenState = MutableStateFlow(CupcakeState())
    val cupcakeState = _screenState.stateIn(
        scope = viewModelScope,
        initialValue = CupcakeRoute.Start,
        started = SharingStarted.WhileSubscribed(5000L)
    )

    fun selectOption(route: String, selectedOption: String) {
        when (route) {
            CupcakeRoute.Start.name -> selectQuantity(selectedOption)
            CupcakeRoute.Flavor.name -> selectFlavor(selectedOption)
            CupcakeRoute.Pickup.name -> selectPickup(selectedOption)
        }
    }

    fun selectScreen(route: String) {
        viewModelScope.launch {
            _screenState.update {
                it.copy(
                    route = route,
//                    quantity = it.quantity,
//                    flavor = it.flavor,
//                    pickup = it.pickup
                )
            }
        }
    }

    fun getList(route: String): List<String> = when (route) {
        CupcakeRoute.Start.name -> arrCupcake
        CupcakeRoute.Flavor.name -> arrFlavor
        CupcakeRoute.Pickup.name -> arrPickup
        else -> listOf()
    }

    fun getSummary(): String {
        val stringBuffer = StringBuffer()

        // cupcake
        stringBuffer.append(_screenState.value.quantity)
        stringBuffer.append("\n")
        stringBuffer.append("\n")

        // flavor
        stringBuffer.append(_screenState.value.flavor)
        stringBuffer.append("\n")
        stringBuffer.append("\n")

        // price
        val price = "15 dollar"
        stringBuffer.append(price)
        stringBuffer.append("\n")

        // pickup
        stringBuffer.append(_screenState.value.pickup)

        return stringBuffer.toString()
    }

    private fun selectQuantity(quantity: String) = viewModelScope.launch {
        _screenState.update {
            it.copy(
                quantity = quantity
            )
        }
    }

    private fun selectFlavor(flavor: String) = viewModelScope.launch {
        _screenState.update {
            it.copy(
                flavor = flavor
            )
        }
    }

    private fun selectPickup(pickup: String) = viewModelScope.launch {
        _screenState.update {
            it.copy(
                pickup = pickup
            )
        }
    }
}

data class CupcakeState(
    val route: String = CupcakeRoute.Start.name,
    val quantity: String = "one",
    val flavor: String = "",
    val pickup: String = ""
)

enum class CupcakeRoute() {
    Start,
    Flavor,
    Pickup,
    Summary
}

val arrCupcake = listOf("one cupcake", "six cupcakes", "twelve cupcakes")
val arrFlavor = listOf("vanilla", "chocolate", "Red velvet", "Salted Caramel", "Coffee")
val arrPickup = listOf("6월 3일", "6월 4일", "6월 5일", "6월 6일")