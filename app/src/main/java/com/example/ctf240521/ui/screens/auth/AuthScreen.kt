package com.example.ctf240521.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.util.Constants
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.LOGIN
import com.example.ctf240521.util.Constants.LOGOUT
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.AuthViewModel
import timber.log.Timber

@Composable
fun RegisterScreen(
    navController: NavHostController
){
    val authVM = hiltViewModel<AuthViewModel>()
    val uiState= authVM.registerStatus.observeAsState()
    uiState.value?.let {
        when(uiState.value?.status){
            Status.SUCCESS -> {
                Toast.makeText(LocalContext.current,it.data ?: "successfully registered",
                    Toast.LENGTH_SHORT).show()}
            Status.ERROR -> {
                Toast.makeText(LocalContext.current,it.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT).show()}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val usernameState= remember{ TextFieldState() }
    val passwordState= remember{ TextFieldState() }
    val repeatPasswordState= remember{ TextFieldState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(70.dp))
        TextFieldOutlined("Username",usernameState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined("Password",passwordState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined("Repeat Password",repeatPasswordState)
        Spacer(Modifier.size(40.dp))
        ButtonClickItem("Register",onClick={
            authVM.registerUser(usernameState.text,passwordState.text,repeatPasswordState.text)
        })
        Spacer(modifier = Modifier.padding(24.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = "Already have an account?",
            text2 = "Login"
        ) {
            navigateRouteFunction(navController,"LoginRoute")
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController
){
    val authVM= hiltViewModel<AuthViewModel>()
    val uiState= authVM.loginStatus.observeAsState()
    uiState.value?.let {
        when(uiState.value?.status){
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current,it.data ?: "successfully logged in",Toast.LENGTH_SHORT
                ).show()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME,authVM.usernamevm).apply()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD,authVM.passwordvm).apply()
                authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                navController.navigate("Home")
                Timber.d("Called")
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current,it.message ?: "An unknown error occured",Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }

    val usernameState= remember{ TextFieldState() }
    val passwordState= remember{ TextFieldState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(70.dp))
        TextFieldOutlined("Username",usernameState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined("Password",passwordState)
        Spacer(Modifier.size(40.dp))
        ButtonClickItem("Login",onClick={
            authVM.loginUser(usernameState.text,passwordState.text)
        })
        Spacer(modifier = Modifier.padding(24.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = "Don't have an account yet?",
            text2 = "Register"
        ) {
            navigateRouteFunction(navController,"RegisterRoute")
        }
    }
}
