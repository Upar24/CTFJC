package com.example.ctf240521.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyAlertDialog(){
    val shouldShowDialog= remember{ mutableStateOf(true)}
    if(shouldShowDialog.value){
        AlertDialog(
            onDismissRequest = {
            shouldShowDialog.value= false
        },
            title= { Text(text="Login",modifier = Modifier.padding(8.dp)) },
            text={
                Column(modifier = Modifier.padding(8.dp)) {
                    TextFieldItem("Username")
                    TextFieldItem("Password")
                }
            },
            confirmButton = {
                Button(
                    onClick={
                        shouldShowDialog.value=false
                    }
                ){
                    Text("OK")
                }
            }

        )
    }
}
