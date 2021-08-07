package com.example.ctf240521.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ctf240521.ui.component.SearchRefreshItem

@Composable
fun CalculationScreen(){
    val listCalc = listOf("DVP","Upgrade","TS","Max Plunder")
    var visibleCalc by remember{ mutableStateOf("DVP")}
    Column {

        var tabIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(selectedTabIndex = tabIndex,modifier= Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent) {
            listCalc.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleCalc=text
                },text={
                    Text(text, Modifier
                        .padding(6.dp),
                        style = if(visibleCalc==text) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                        color=if(visibleCalc==text) Color.Magenta else MaterialTheme.colors.onBackground)
                })
            }
        }
        Text(visibleCalc)
        SearchRefreshItem(desc = "Search",onClick = {visibleCalc=="TS"})
    }
}