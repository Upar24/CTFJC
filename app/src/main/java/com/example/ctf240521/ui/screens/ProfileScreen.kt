package com.example.ctf240521.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.ButtonClickItem
import com.example.ctf240521.ui.component.ProfileInfoItem
import com.example.ctf240521.ui.component.WallCard

@Composable
fun ProfileScreen(){
    var seeMore by remember { mutableStateOf(false) }
    var visibleProfile by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween){
            Text("Username")
            Text("Name")
            Text("IGN")
            Text("Club Name")
        }
        if(seeMore){
            Text("SomeMore lmao")
            Text("SomeMore lmao")
            Text("SomeMore lmao")
            Text("SomeMore lmao")
            Text("SomeMore lmao")
            Text("SomeMore lmao")
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { seeMore = !seeMore },Arrangement.End){
                Text("Show Less")
                Image(
                    painterResource(id = R.drawable.up_arrow),
                    contentDescription = "Search Menu",
                    modifier= Modifier
                        .height(36.dp)
                        .clickable { seeMore = !seeMore }
                )
            }
        }else{
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { seeMore = !seeMore },Arrangement.End){
                Text("Show More")
                Image(
                    painterResource(id = R.drawable.down_arrow),
                    contentDescription = "Search Menu",
                    modifier= Modifier
                        .height(36.dp)
                        .clickable { seeMore = !seeMore }
                )
            }
        }
        Text(visibleProfile)
        var tabIndex by remember { mutableStateOf(0)}
        val profileList = listOf("wall","post")
        TabRow(selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent) {
            profileList.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleProfile=text
                },text={
                    Text(text)
                })
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
        WallCard()
    }
}
