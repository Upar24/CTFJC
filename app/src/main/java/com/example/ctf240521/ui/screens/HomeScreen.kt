package com.example.ctf240521.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.util.Constants
import com.example.ctf240521.viewmodel.RegisterViewModel

@Composable
fun HomeScreen(vm: RegisterViewModel = viewModel()){
    Text("Home Screen Hmm LOL${vm.passwordvm} ${vm.usernamevm} " +
            (vm.sharedPref.getString(
                Constants.KEY_LOGGED_IN_USERNAME,
                Constants.NO_USERNAME
            ) ?: Constants.NO_USERNAME)
    )
    if(vm.isLoggedIn()){
        vm.usernamevm?.let { vm.passwordvm?.let { it1 -> vm.authenticateApi(it, it1) } }
    }
}