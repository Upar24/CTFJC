package com.example.ctf240521.ui.component

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf240521.R
import com.example.ctf240521.data.local.entities.User
import com.example.ctf240521.data.local.entities.Wall
import com.example.ctf240521.data.remote.requests.UpdateUserRequest
import com.example.ctf240521.ui.screens.BottomNavigationScreens
import com.example.ctf240521.ui.screens.profile.ProfileViewModel
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfileInfoItem(number:String,desc:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style =MaterialTheme.typography.button
        )
        Text(
            text=desc,
            style= MaterialTheme.typography.body2
        )
    }
}
@Composable
fun DividerItem(){
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 7.dp,top=7.dp)
    )
}
@Composable
fun SwitchTOLoginOrRegisterTexts(
    modifier: Modifier,
    text1: String,
    text2: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text2,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button,
            modifier = Modifier.clickable { onClick() }
        )
    }
}
@Composable
fun TextFieldOutlined(desc:String,state: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text=desc)},
        value =state.text,
        onValueChange = {
            state.text = it
        }
    )
}
@Composable
fun ButtonClickItem(desc: String,onClick: () -> Unit,warna : Color= Color.Unspecified) {
    Button(
        onClick =  onClick,
        border=BorderStroke(2.dp,warna),
        shape = RoundedCornerShape(12.dp)
    ){
        Text(desc,style=MaterialTheme.typography.button,color= MaterialTheme.colors.onSurface)
    }
}
@Composable
fun CardParty(desc: String){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.onSurface),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column(
            Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(desc,style=MaterialTheme.typography.body2)
            Text("CC",style = MaterialTheme.typography.h1)
            Text("3 hrs",style = MaterialTheme.typography.caption)
        }
    }
}
@Composable
fun ProgressBarItem(){
    Row(
        verticalAlignment =Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
            Spacer(Modifier.size(10.dp))
            Text(text="Please wait..")
        }
    }
}
@Composable
fun ProgressCardToastItem(){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(12.dp),
            shape= RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ){
            ProgressBarItem()
        }
    }
}
@Composable
fun TopBarItem(onIconClick: () -> Unit,modifier: Modifier){
    Row(modifier= modifier
        .height(45.dp)
        .background(color = MaterialTheme.colors.onError),
        Arrangement.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(id = R.drawable.list),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClick = onIconClick
                    )
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = "CTForever",
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5
            )
        }
    }
}
@Composable
fun ChatCard(){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.onSurface),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp)){
                Row(Modifier.weight(1f)) {
                    Text("UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",Modifier.fillMaxWidth())
                }
                Row(Modifier.weight(1f)) {
                    Text("UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",Modifier.fillMaxWidth())
                }
                Spacer(Modifier.padding(3.dp))
                Row(Modifier.weight(1f)) {
                    Text("UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",Modifier.fillMaxWidth())
                }
            }
            DividerItem()
            Text("At here will be the description about what they gonna post so " +
                    "no need to worry about this gonna be post lol i hope you " +
                    "will get this common sense. get it?",Modifier.padding(6.dp),textAlign=TextAlign.Justify)
        }
    }
}
@Composable
fun TradingCard() {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(Modifier.padding(6.dp)) {

            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(Modifier.weight(1f)) {
                    Text(
                        "UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",
                        Modifier.fillMaxWidth()
                    )
                }
                Row(Modifier.weight(1f)) {
                    Text(
                        "UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",
                        Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.padding(3.dp))
                Row(Modifier.weight(1f)) {
                    Text(
                        "UsernameUsernameUsernameUsernameUsernameUsernameUsernameUsername",
                        Modifier.fillMaxWidth()
                    )
                }
            }
            DividerItem()
            Text("title", style = MaterialTheme.typography.caption)
            Text("Heres your title trading", textAlign = TextAlign.Justify)
            Text("description", style = MaterialTheme.typography.caption)
            Text("Heres your description trading", textAlign = TextAlign.Justify)
            Text("Buying", style = MaterialTheme.typography.caption)
            Text("8 papers")
            Text("For", style = MaterialTheme.typography.caption)
            Text("8 papers")
        }
    }
}
@Composable
fun WallCard(wall: Wall?,onWall: () -> Unit,onDelete:() ->Unit){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column {
            wall?.let {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    it.username?.let { it1 -> Text(it1) }
//                    val dateFormat=SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
//                    val dateString= dateFormat.format(it.date ?: "Date")
//                    Text(dateString)
                }
                DividerItem()
                Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween) {
                    it.chat?.let { it1 -> Text(it1) }
                    ButtonClickItem(desc = "Wall", onClick = onWall)
                    if(it.username==it.wallOwner) ButtonClickItem(desc = "Delete",onClick =onDelete )
                }
            }
        }
    }
}
@Composable
fun UserProfile(user:User?){

    if(user?.username!=""){

        user?.let {
            Column() {
                Text(it.name.toString())
                Text(it.ign.toString())
                Text(it.clubName.toString())
                Text(it.bio.toString())
                it.bio?.let { Text(it) }
                Text(it.bio!!)
                Text(it.bio!!)
                Text(text = it.bio!!)
                Text(it.bio!!)
            }
        }
    }
}
@Composable
fun EditProfileDialog(user: User){
    var openEdit by remember { mutableStateOf(true)}
    val profileVM = hiltViewModel<ProfileViewModel>()
    val nameState = remember { TextFieldState(user.name.toString()) }
    val clubNameState = remember { TextFieldState(user.clubName.toString()) }
    val ignState = remember { TextFieldState(user.ign.toString()) }
    val bioState = remember { TextFieldState(user.bio.toString()) }
    val uiState=profileVM.updateProfile.observeAsState()
    uiState.value?.let {
        when(it.status){
            Status.SUCCESS ->{
                Toast.makeText(
                    LocalContext.current,it.message ?: "Profile Updated", Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current,it.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }

    if(openEdit){
        AlertDialog(
            onDismissRequest={openEdit = false},
            title = { Text(text = "Edit Profilfe") },
            text = {
                Column(
                    Modifier
                        .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(12.dp))
                    Spacer(modifier = Modifier.padding(12.dp))
                    TextFieldOutlined(desc = "Name", nameState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "Club Name", clubNameState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "IGN", ignState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "Bio", bioState)
                    Spacer(modifier = Modifier.padding(6.dp))
                }
            },
            confirmButton ={ Button(onClick = {
                profileVM.updateProfile(
                    UpdateUserRequest(
                        name = nameState.text,
                        clubName = clubNameState.text,
                        ign = ignState.text,
                        bio = bioState.text
                    )
                )
            }){
                Text("Save")
            }},
            dismissButton = {
                Button(
                    onClick = {openEdit= false}
                ){
                    Text("Cancel")
                }
            }
        )
    }
}
@Composable
fun WallList(wallList: List<Wall>,navController: NavHostController) {
    wallList.forEach {
        val profileVM= hiltViewModel<ProfileViewModel>()
        val authVM= hiltViewModel<AuthViewModel>()
        authVM.isLoggedIn()
        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
        WallCard(wall = it,onWall = {navController.navigate(BottomNavigationScreens.OtherProfile.withArgs(it.username.toString()))},onDelete = {profileVM.deleteWall(it)})
 }
}
@Preview
@Composable
fun X(){
}
