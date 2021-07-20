package com.example.ctf240521.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctf240521.ui.component.ChatCard
import com.example.ctf240521.ui.component.DividerItem

@Composable
fun ChatScreen(){
    Column (
        Modifier
            .fillMaxSize()
            .padding(6.dp)){
        val listChat = listOf("lbhpost","lbhneed","hotsale","random",)
        var visibleChat by remember{ mutableStateOf("LBH Post") }
        Spacer(Modifier.padding(6.dp))
        var tabIndex by remember { mutableStateOf(1)}
                ScrollableTabRow(selectedTabIndex = tabIndex,Modifier.fillMaxWidth(),
                    backgroundColor =Color.Transparent) {
                    listChat.forEachIndexed { index,text ->
                        Tab(selected=tabIndex==index,onClick={
                            tabIndex=index
                            visibleChat=text
                        },text= {
                            Text(
                                when (text) {
                                    "lbhpost" -> "Lbh Post"
                                    "lbhneed" -> "Lbh needed"
                                    "hotsale" -> "Hot sale"
                                    else -> "Random"
                                }, Modifier
                                    .padding(6.dp)
                                    .clickable { visibleChat = text },
                                style = if (visibleChat == text) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                                color = if (visibleChat == text) Color.Magenta else MaterialTheme.colors.onBackground
                            )
                        }
                        )
                    }
                }
        DividerItem()
        Row (Modifier.fillMaxWidth(),Arrangement.SpaceEvenly){
            Text(text = "Add New Chat")
            Text("Refresh Chat")
        }
        Text(text = visibleChat)
        ChatCard()
    }
}
@Preview
@Composable
fun Lmao(){
    ChatScreen()
}