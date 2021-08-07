package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ctf240521.data.local.entities.Chat
import com.example.ctf240521.ui.component.ChatCard
import com.example.ctf240521.ui.component.DividerItem
import com.example.ctf240521.ui.component.ProgressCardToastItem
import com.example.ctf240521.ui.screens.home.HomeViewModel
import com.example.ctf240521.util.Status

@Composable
fun ChatScreen(){
    Column (
        Modifier
            .fillMaxSize()
            .padding(6.dp)){
        val homeVM = hiltViewModel<HomeViewModel>()
        val listChat = listOf("lbhpost","lbhneed","hotsale","random",)
        var visibleChat by remember{ mutableStateOf("LBH Post") }
        Spacer(Modifier.padding(6.dp))
        val saveChatState=homeVM.saveChatStatus.observeAsState()
        var chatList= listOf<Chat>()
        saveChatState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS ->{
                    Toast.makeText(
                        LocalContext.current,result.message ?: "Chat sent", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val getChatsState=homeVM.getChatStatus.observeAsState()
        getChatsState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    chatList = it.data ?: return@let
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
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
        chatList.forEach {
            if(it.type==visibleChat){
                ChatCard(it)
            }
        }
    }
}
@Preview
@Composable
fun Lmao(){
    ChatScreen()
}