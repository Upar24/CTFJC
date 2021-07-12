package com.example.ctf240521.ui.screens

import androidx.compose.foundation.layout.*
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
    var visibleProperty by remember { mutableStateOf("")}
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp)
    ) {
        Row (Modifier.fillMaxWidth(),Arrangement.SpaceEvenly){
            ButtonClickItem(
                desc = "Party",
                onClick = {visibleProperty = "party"},
                warna = if (visibleProperty == "party") Color.Magenta else Color.Unspecified
            )
            ButtonClickItem(
                desc = "Calculation",
                onClick = { visibleProperty = "calc" },
                warna = if (visibleProperty == "calc") Color.Magenta else Color.Unspecified
            )
            ButtonClickItem(
                desc = "Chat",
                onClick = { visibleProperty = "chat" },
                warna = if (visibleProperty == "chat") Color.Magenta else Color.Unspecified
            )
        }
        when(visibleProperty){
            "party" -> PartyScreen()
            "calc" -> CalculationScreen()
            "chat" -> ChatScreen()
        }
    }
}
