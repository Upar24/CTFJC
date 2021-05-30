package com.example.ctf240521.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ctf240521.R
import com.example.ctf240521.ui.screens.SearchScreen

@Composable
fun CTFAppTopNavigation(
    onIconClick: () -> Unit,
    navController: NavHostController
){

    Row(
        modifier= Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primarySurface),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painterResource(id = R.drawable.list),
                contentDescription = "List Top Navigation",
                modifier = Modifier
                    .clickable(onClick = onIconClick)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "CTForever",
                color = MaterialTheme.colors.onPrimary,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    letterSpacing = 0.15.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
        Image(
            painterResource(id = R.drawable.search),
            contentDescription = "Search Menu",
            modifier= Modifier
                .clickable {
                    navController.navigate("Search"){
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it){
                                saveState=true
                            }
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                }
                .height(40.dp)
                .padding(10.dp, end = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}








