package com.example.ctf240521.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ctf240521.ui.screens.BottomNavigationScreens

@Composable
fun CTFAppBottomNavigation(
    navController: NavHostController,
    items:List<BottomNavigationScreens>
){
    BottomNavigation {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute= navBackStackEntry?.destination?.route
        items.forEach{screen ->
            BottomNavigationItem(
                icon= { Image(
                    painterResource(id =screen.icon),
                    contentDescription = screen.route,
                    modifier = Modifier.height(30.dp)
                )
                },
                label={
                    Text(
                        stringResource(id = screen.resourceId),
                    color= Color.LightGray,
                    fontSize = 14.sp,fontWeight = FontWeight.Bold
                )
                },
                selected = currentRoute==screen.route,
                alwaysShowLabel= false,
                onClick = {
                    navController.navigate(screen.route){
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it){
                                saveState=true
                            }
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                },
            )
        }
    }
}