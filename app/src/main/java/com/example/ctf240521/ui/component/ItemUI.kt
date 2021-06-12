package com.example.ctf240521.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun SwitchTOLoginOrRegisterTexts(
    modifier: Modifier,
    text1: String,
    text2: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text2,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { onClick() }
        )
    }
}
@Composable
fun TextFieldItem(lol:String) {
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
fun UsernameField(usernameState: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text="Username")},
        value =usernameState.text,
        onValueChange = {
            usernameState.text = it
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
fun RepeatePasswordField(repeatePasswordState: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text="Repeat Password")},
        value =repeatePasswordState.text,
        onValueChange = {
            repeatePasswordState.text = it
        }
    )
}

@Composable
fun RegisterButton(desc: String,onValidate: () -> Unit) {
    Button(
        onClick =  onValidate
    ){
        Text(desc)
    }
}
@Composable
fun ProgressBarItem(){
    Row(
        verticalAlignment =Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
            Spacer(Modifier.size(10.dp))
            Text(
                text="Please wait.."
            )
        }

    }
}