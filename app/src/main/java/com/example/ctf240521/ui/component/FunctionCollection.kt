package com.example.ctf240521.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController


class TextFieldState{
    var text : String by mutableStateOf("")
}

fun navigateRouteFunction(
    navController: NavHostController,
    route:String
){
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}