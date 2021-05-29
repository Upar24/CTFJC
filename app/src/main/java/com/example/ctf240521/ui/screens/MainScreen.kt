package com.example.ctf240521.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.CTFAppBottomNavigation

sealed class BottomNavigationScreens(
    val route:String,
    @StringRes val resourceId:Int,
    val icon: Int
){
    object Home:BottomNavigationScreens("Home",R.string.home_screen_route, R.drawable.home)
    object Party:BottomNavigationScreens("Party",R.string.party_screen_route, R.drawable.party)
    object Add:BottomNavigationScreens("Add",R.string.add_screen_route, R.drawable.add)
    object Trading:BottomNavigationScreens("Trading",R.string.trading_screen_route, R.drawable.trading)
    object Profile:BottomNavigationScreens("Profile",R.string.profile_screen_route, R.drawable.profile)
}


@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val bottomNavigationItems= listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Party,
        BottomNavigationScreens.Add,
        BottomNavigationScreens.Trading,
        BottomNavigationScreens.Profile
    )
    
    Scaffold (
        bottomBar ={
            CTFAppBottomNavigation(navController , bottomNavigationItems )
        },
    ){
        MainScreenNavigationConfiguration(navController)
    }
}


@Composable
fun MainScreenNavigationConfiguration(
    navController: NavHostController
){
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route){
        composable(BottomNavigationScreens.Home.route){
            HomeScreen()
        }
        composable(BottomNavigationScreens.Party.route){
            PartyScreen()
        }
        composable(BottomNavigationScreens.Add.route){
            AddScreen()
        }
        composable(BottomNavigationScreens.Trading.route){
            TradingScreen()
        }
        composable(BottomNavigationScreens.Profile.route){
            ProfileScreen()
        }
    }
}
