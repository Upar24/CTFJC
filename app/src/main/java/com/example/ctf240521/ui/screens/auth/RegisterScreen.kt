package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.RegisterViewModel
import com.example.ctf240521.viewmodel.TextFieldState

@Composable
fun RegisterScreen(navController: NavHostController,vm: RegisterViewModel= viewModel()){

        val uiState= vm.registerStatus.observeAsState()
        Register(navController,vm)
        uiState.value?.let {
                when(uiState.value?.status){
                        Status.SUCCESS -> {
                                Toast.makeText(
                                        LocalContext.current,
                                        uiState.value?.data ?: "successfully registered",
                                        Toast.LENGTH_SHORT
                                )
                                        .show()
                        }
                        Status.ERROR -> {
                                Toast.makeText(
                                        LocalContext.current,
                                        uiState.value?.message ?: "An unknown error occured",
                                        Toast.LENGTH_SHORT
                                )
                                        .show()
                        }
                        Status.LOADING -> {
                                ProgressBarItem()
                        }
                }
        }
        Text("Register Screen Hmm LOL")
}
@Composable
fun Register(navController: NavHostController, vm: RegisterViewModel) {
        val usernameState= remember{ TextFieldState() }
        val passwordState= remember{ TextFieldState() }
        val repeatPasswordState= remember{ TextFieldState() }
        Column(
                modifier = Modifier.fillMaxSize().padding(bottom=20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ){
                Spacer(Modifier.size(70.dp))
                TextFieldOutlined("Username",usernameState)
                Spacer(Modifier.size(7.dp))
                TextFieldOutlined("Password",passwordState)
                Spacer(Modifier.size(7.dp))
                TextFieldOutlined("Repeat Password",repeatPasswordState)
                Spacer(Modifier.size(40.dp))
                ButtonClickItem("Register",onValidate={
                        vm.registerUser(usernameState.text,passwordState.text,repeatPasswordState.text)
                })
                Spacer(modifier = Modifier.padding(24.dp))
                SwitchTOLoginOrRegisterTexts(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        text1 = "Don't have an account yet?",
                        text2 = "Login"
                ) {
                        navController.navigate("LoginRoute"){
                                navController.graph.startDestinationRoute?.let {
                                        popUpTo(it){
                                                saveState=true
                                        }
                                }
                                launchSingleTop=true
                                restoreState=true
                        }
                }
        }


}