package com.example.ctf240521.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.*

@Composable
fun AddScreen(){
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp)
    ) {

        var visibleScreen by remember { mutableStateOf("") }
        val searchState= remember{ TextFieldState() }
        val addScreenList = listOf("buying","all","selling")
        Row(
            Modifier
                .fillMaxWidth()
                .padding(6.dp),Arrangement.Center,Alignment.CenterVertically){
            TextFieldOutlined(desc = "Search",searchState,Modifier.height(60.dp))
            Spacer(Modifier.padding(6.dp))
            Image(
                painterResource(id = R.drawable.search),
                contentDescription = "Search Menu",
                modifier= Modifier
                    .height(36.dp)
                    .clickable {}
            )
        }

        var tabIndex by remember { mutableStateOf(0)}
        ScrollableTabRow(selectedTabIndex = tabIndex,
            backgroundColor =Color.Transparent) {
            addScreenList.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleScreen=text
                },modifier=Modifier.fillMaxWidth(0.5f),text={
                    if(text=="."){
                        Text(text,fontSize= 0.01.sp)
                    }else{
                        Text(text)
                    }
                })
            }
        }
        when(visibleScreen){
            "buying" -> Text("buying")
            "selling" -> Text("selling")
            "all" -> Text("all")
        }
        TradingCard()
    }
}