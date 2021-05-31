package com.example.ctf240521.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ctf240521.R

@Composable
fun CTFAppDrawerNavigation(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit
){
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ){
        AppdrawerHeader(closeDrawerAction)
//        AppdrawerBody(closeDrawerAction)
//        AppdrawerFooter(modifier)
    }
}
@Composable
private fun AppdrawerHeader(closeDrawerAction: () -> Unit) {
    Column(
        modifier =Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primarySurface)
        ) {
            Image(
                painterResource(id = R.drawable.list),
                contentDescription = "List Top Navigation",
                modifier = Modifier
                    .clickable(onClick = closeDrawerAction)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "CTForever",
                color = MaterialTheme.colors.onPrimary,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    letterSpacing = 0.15.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
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
@Composable
private fun ProfileInfoItem(
    number:String,
    desc:String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text=desc
        )
    }
}
@Preview
@Composable
private fun ProfileInfoItemPreview(){
    ProfileInfoItem("8","lol")
}