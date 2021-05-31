package com.example.ctf240521.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ctf240521.R
import com.example.ctf240521.ui.screens.BottomNavigationScreens
import com.google.android.material.internal.NavigationMenu

@Composable
fun CTFAppDrawerNavigation(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items:List<BottomNavigationScreens>
){
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ){
        AppdrawerHeader(closeDrawerAction)
        AppdrawerBody(closeDrawerAction,navController,items)
//        AppdrawerFooter(modifier)
    }
}
@Composable
fun AppdrawerHeader(closeDrawerAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primarySurface)
        ) {
            Image(
                painterResource(id = R.drawable.list),
                contentDescription = "List Top Navigation",
                modifier = Modifier
                    .clickable(onClick = closeDrawerAction)
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
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.image),
                    modifier = Modifier
                        .padding(start = 14.dp, top = 4.dp, bottom = 4.dp)
                        .size(80.dp),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    contentDescription = "Foto Profile"
                )
                Text(
                    text="Username",//stringResource(R.string.default_username)
                    style=TextStyle(fontSize = 20.sp),
                    modifier=Modifier.padding(bottom=5.dp),
                    color= MaterialTheme.colors.primaryVariant
                )
            }
            ProfileInfoItem(number = "8", desc = "CTF Coins" )
        }
    }
    Divider(
        color= MaterialTheme.colors.onSurface.copy(alpha=.2f),
        modifier = Modifier.padding(start = 7.dp,end=7.dp,top=7.dp)
    )
}
@Composable
fun AppdrawerBody(
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute= navBackStackEntry?.destination?.route
    items.forEach{item ->
//        BottomNavigationItem(
//            icon= { Image(
//                painterResource(id =screen.icon),
//                contentDescription = screen.route,
//                modifier = Modifier.height(30.dp)
//            )
//            },
//            label={
//                Text(
//                    stringResource(id = screen.resourceId),
//                    color= Color.LightGray,
//                    fontSize = 14.sp,fontWeight = FontWeight.Bold
//                )
//            },
//            selected = currentRoute==screen.route,
//            alwaysShowLabel= false,
//            onClick = {
//                navController.navigate(screen.route){
//                    navController.graph.startDestinationRoute?.let {
//                        popUpTo(it){
//                            saveState=true
//                        }
//                    }
//                    launchSingleTop=true
//                    restoreState=true
//                }
//            },
//        )
//    }
    }
}

@Composable
fun ProfileInfoItem(
    number:String,
    desc:String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Center
            ),
//            modifier = Modifier
//                .padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text=desc,
            style= TextStyle(fontSize = 16.sp)
        )
    }
}
@Preview
@Composable
private fun ProfileInfoItemPreview(){
    ProfileInfoItem("8","lol")
}