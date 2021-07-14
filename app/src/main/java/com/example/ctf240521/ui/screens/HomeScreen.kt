package com.example.ctf240521.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.ui.component.ButtonClickItem
import com.example.ctf240521.ui.screens.post.PostViewModel
import com.example.ctf240521.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    authVM: AuthViewModel = viewModel(),
    postVM: PostViewModel = viewModel(),
) {
    var visibleHome by remember { mutableStateOf("party")}
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp)
    ) {
        var tabIndex by remember { mutableStateOf(1)}
        var homeItem = listOf("party","calculation","chat")
        TabRow(selectedTabIndex = tabIndex,Modifier.fillMaxWidth(),
            backgroundColor =Color.Transparent) {
            homeItem.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleHome=text
                },text={
                    Text(text,color=if (visibleHome == text) Color.Magenta else Color.Unspecified)
                })
            }
        }
        when(visibleHome){
            "party" -> PartyScreen()
            "calculation" -> CalculationScreen()
            "chat" -> ChatScreen()
        }
    }
}
