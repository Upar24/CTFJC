package com.example.ctf240521

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctf240521.ui.theme.CTF240521Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTF240521Theme {
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
//
//@Preview(showBackground = true)k
//@Composable
//fun DefaultPreview() {
//    CTF240521Theme {
//        Greeting("Android")
//    }
//}