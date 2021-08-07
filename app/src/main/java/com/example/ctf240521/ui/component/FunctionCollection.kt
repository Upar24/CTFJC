package com.example.ctf240521.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ctf240521.data.local.entities.Party
import com.example.ctf240521.data.local.entities.User
import com.example.ctf240521.ui.screens.home.HomeViewModel
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.AuthViewModel


class TextFieldState(string: String=""){
    var text : String by mutableStateOf(string)
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