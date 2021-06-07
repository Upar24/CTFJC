package com.example.ctf240521.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.ctf240521.ui.component.EmailField
import com.example.ctf240521.ui.component.LoginButton
import com.example.ctf240521.ui.component.PasswordField

class LoginViewModel : ViewModel(){
    @Composable
    fun Login(){
        var emailState= remember{TextFieldState()}
        var passwordState= remember{TextFieldState()}
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            EmailField(emailState)
            PasswordField(passwordState)
            LoginButton(onValidate={
                loginUser(email= emailState.toString(), password= passwordState.toString())
            })
        }


    }
}
fun loginUser(email:String,password:String){
}
class TextFieldState(){
    var text : String by mutableStateOf("")
}