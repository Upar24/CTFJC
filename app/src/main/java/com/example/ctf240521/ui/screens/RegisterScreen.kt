package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.RegisterViewModel
import com.example.ctf240521.viewmodel.TextFieldState

@Composable
fun RegisterScreen(vm: RegisterViewModel= viewModel()){

        val uiState= vm.registerStatus.observeAsState()
        Register(vm)
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
fun Register(vm: RegisterViewModel) {
        var usernameState= remember{ TextFieldState() }
        var passwordState= remember{ TextFieldState() }
        var repeatPasswordState= remember{ TextFieldState() }
        Column(
                modifier = Modifier.fillMaxSize().padding(bottom=20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ){
                Spacer(Modifier.size(70.dp))
                UsernameField(usernameState)
                Spacer(Modifier.size(7.dp))
                PasswordField(passwordState)
                Spacer(Modifier.size(7.dp))
                RepeatePasswordField(repeatPasswordState)
                Spacer(Modifier.size(40.dp))
                RegisterButton(onValidate={
                        vm.registerUser(usernameState.text,passwordState.text,repeatPasswordState.text)
                })
        }


}