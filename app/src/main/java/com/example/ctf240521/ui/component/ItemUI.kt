package com.example.ctf240521.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.viewmodel.LoginViewModel
import com.example.ctf240521.viewmodel.TextFieldState

@Composable
fun ProfileInfoItem(number:String,desc:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            text=desc,
            style= TextStyle(fontSize = 16.sp)
        )
    }
}
@Composable
fun Divider(){
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
        modifier = Modifier.padding(start = 7.dp, end = 7.dp, bottom = 7.dp)
    )
}
@Composable
public fun TextFieldItem(lol:String) {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        label={Text(text=lol)},
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
    )
}
@Composable
fun EmailField(emailState: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text="Email")},
        value =emailState.text,
        onValueChange = {
            emailState.text = it
        }
    )
}
@Composable
fun PasswordField(passwordState: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text="Password")},
        value =passwordState.text,
        onValueChange = {
            passwordState.text = it
        }
    )
}
@Composable
fun LoginButton(onValidate: () -> Unit) {
    Button(
        onClick =  onValidate
    ){
        Text("Login")
    }
}