package com.example.ctf240521.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.ProfileInfoItem

@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.image),
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            contentDescription = "Foto Profile"
        )
        Text(
            text="Username",//stringResource(R.string.default_username)
            color= MaterialTheme.colors.primaryVariant
        )
        ProfileInfoItem(number = "8", desc = "days CTF" )
        Row (modifier= Modifier.fillMaxWidth().padding(start=20.dp,end=20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            ProfileInfoItem(number = "12", desc = "Followers")
            ProfileInfoItem(number = "24", desc = "Following")
        }
        Row (modifier= Modifier.fillMaxWidth().padding(start=20.dp,end=20.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            ProfileInfoItem(number = "9", desc = "Days Streak")
            ProfileInfoItem(number = "6", desc = "CTF Coins")
        }
    }
    Divider(
        color= MaterialTheme.colors.onSurface.copy(alpha=.2f),
        modifier = Modifier.padding(start = 16.dp,end=16.dp,top=16.dp)
    )
}
