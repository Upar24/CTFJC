package com.example.ctf240521.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.CTFAppBottomNavigation
import com.example.ctf240521.ui.component.CTFAppDrawerNavigation
import com.example.ctf240521.ui.component.CTFAppTopNavigation
import kotlinx.coroutines.launch

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
    object Search:BottomNavigationScreens("Search",R.string.search_screen_route, R.drawable.search)
}


@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scaffoldState:ScaffoldState= rememberScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    val bottomNavigationItems= listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Party,
        BottomNavigationScreens.Add,
        BottomNavigationScreens.Trading,
        BottomNavigationScreens.Profile
    )
    Scaffold (
        topBar={
            CTFAppTopNavigation(
                onIconClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                navController,
                BackHandler(onBack = {
                    if(scaffoldState.drawerState.isOpen){
                        coroutineScope.launch { scaffoldState.drawerState.close() }
                    }else{
                            navController.navigate("Home")
                        }
                    }
                )

            )
        },
        scaffoldState=scaffoldState,
        drawerContent={
            CTFAppDrawerNavigation {
            }

        },
        bottomBar ={
            CTFAppBottomNavigation(navController , bottomNavigationItems )
        }
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
        composable(BottomNavigationScreens.Search.route){
            SearchScreen()
        }

    }
}
