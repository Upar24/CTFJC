package com.example.ctf240521.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctf240521.ui.component.ChatCard
import com.example.ctf240521.ui.component.DividerItem
import java.time.format.TextStyle

@Composable
fun ChatScreen(){
    Column (
        Modifier
            .fillMaxSize()
            .padding(6.dp)){
        val listChat = listOf("lbhpost","lbhneed","hotsale","random",)
        var visibleChat by remember{ mutableStateOf("LBH Post") }
        Spacer(Modifier.padding(6.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()), Arrangement.Center) {
            listChat.forEach{
                Text(
                    text = when(it){
                        "lbhpost" ->"Lbh Post"
                        "lbhneed" -> "Lbh needed"
                        "hotsale" -> "Hot sale"
                        else -> "Random"
                    },
                    Modifier
                        .padding(6.dp)
                        .clickable { visibleChat = it },
                    style = if(visibleChat==it) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                    color=if(visibleChat==it) Color.Magenta else MaterialTheme.colors.onBackground
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
fun lmao(){
    ChatScreen()
}