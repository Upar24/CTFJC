package com.example.ctf240521

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ctf240521.ui.screens.MainScreen
import com.example.ctf240521.ui.theme.CTF240521Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTF240521Theme {
                    MainScreen()
            }
        }
    }
}