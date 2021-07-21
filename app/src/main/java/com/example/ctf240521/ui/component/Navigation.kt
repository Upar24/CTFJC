package com.example.ctf240521.ui.component
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ctf240521.R
import com.example.ctf240521.ui.screens.BottomNavigationScreens
import com.example.ctf240521.ui.theme.ThemeState
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.LOGIN
import com.example.ctf240521.util.Constants.LOGOUT
import com.example.ctf240521.util.Constants.NO_PASSWORD
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.AuthViewModel

@Composable
fun CTFAppTopNavigation(
    onIconClick: () -> Unit,
    navController: NavHostController
){
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onError)
            .padding(start = 12.dp, end = 12.dp)
            .height(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        TopBarItem(onIconClick, modifier = Modifier)
        Image(
            painterResource(id = R.drawable.search),
            contentDescription = "Search Menu",
            modifier= Modifier
                .clickable {
                    navigateRouteFunction(navController,"Search")
                }
        )
    }
}


@Composable
fun CTFAppDrawerNavigation(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items: List<BottomNavigationScreens>
){
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ){
        AppdrawerHeader(
            closeDrawerAction
        )
        Divider()
        AppdrawerBody(
            closeDrawerAction,
            navController,
            items
        )
        Divider()
        AppdrawerFooter(
            navController,
            closeDrawerAction
        )
    }
}
@Composable
fun AppdrawerHeader(
    closeDrawerAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val authVM = hiltViewModel<AuthViewModel>()
        val username=authVM.sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME
        TopBarItem(
            closeDrawerAction,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text=username,
            style=MaterialTheme.typography.subtitle2
        )
        }
    }

@Composable
fun AppdrawerBody(
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute= navBackStackEntry?.destination?.route
    items.forEach {item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    navigateRouteFunction(navController, item.route)
                    closeDrawerAction()
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(item.icon),
                contentDescription = "apa aj",
                modifier = Modifier
                    .height(36.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(item.resourceId),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
@Composable
fun AppdrawerFooter(
    navController: NavHostController,
    closeDrawerAction: () -> Unit
){

    val authVM = hiltViewModel<AuthViewModel>()
    authVM.getDesc()
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        val x by authVM.desc.collectAsState()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(48.dp)
                .padding(12.dp)
        ){
            Text(
                "Turn on dark theme",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.clickable {val theme = when(ThemeState.darkModeState){
                    true -> AppCompatDelegate.MODE_NIGHT_NO
                    false -> AppCompatDelegate.MODE_NIGHT_YES
                }
                    AppCompatDelegate.setDefaultNightMode(theme)
                    ThemeState.darkModeState = !ThemeState.darkModeState
                }
            )
            Switch(
                checked = ThemeState.darkModeState,
                onCheckedChange = { ThemeState.darkModeState = it }
            )
        }
        Button(
            onClick = {
                closeDrawerAction()
                if (x == LOGIN) {
                    navigateRouteFunction(navController, "LoginRoute")
                    authVM.getDesc()
                } else {
                    authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME, NO_USERNAME).apply()
                    authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD, NO_PASSWORD).apply()
                    navController.navigate("Home")
                    authVM.getDesc()
                }
            }
        ) {
            Text(x)
        }
    }
}
@Composable
fun CTFAppBottomNavigation(
    navController: NavHostController,
    items:List<BottomNavigationScreens>
){
    BottomNavigation (backgroundColor=MaterialTheme.colors.onError){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute= navBackStackEntry?.destination?.route
        items.forEach{screen ->
            BottomNavigationItem(
                icon= { Image(
                    painterResource(id =screen.icon),
                    contentDescription = screen.route,
                    modifier = Modifier.height(36.dp)
                )
                },
                label={
                    Text(
                        stringResource(id = screen.resourceId),
                        color= MaterialTheme.colors.onPrimary,
                        fontSize = 18.sp,fontWeight = FontWeight.Bold
                    )
                },
                selected = currentRoute==screen.route,
                alwaysShowLabel= false,
                onClick = {
                    navigateRouteFunction(navController,screen.route)
                    }

            )
        }
    }
}


