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
import com.example.ctf240521.data.local.entities.*
import com.example.ctf240521.data.remote.requests.UpdateUserRequest
import com.example.ctf240521.ui.screens.BottomNavigationScreens
import com.example.ctf240521.ui.screens.TradingScreen
import com.example.ctf240521.ui.screens.add.AddViewModel
import com.example.ctf240521.ui.screens.home.HomeViewModel
import com.example.ctf240521.ui.screens.profile.ProfileViewModel
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.AuthViewModel

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
fun SwitchTOLoginOrRegisterTexts(modifier: Modifier,text1: String,text2: String,onClick: () -> Unit) {
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
fun CardDrop(dropped: Dropped){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.onSurface),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column(
            Modifier
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text("Day ${dropped.day}",style=MaterialTheme.typography.body2)
            Text(dropped.name,style = MaterialTheme.typography.h1)
            Text("${dropped.duration} hrs",style = MaterialTheme.typography.caption)
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
fun ChatCard(chat: Chat){
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
                    Text(chat.username.toString(),Modifier.fillMaxWidth())
                }
                Row(Modifier.weight(1f)) {
                    Text(chat.clubName.toString(),Modifier.fillMaxWidth())
                }
                Spacer(Modifier.padding(3.dp))
                Row(Modifier.weight(1f)) {
                    Text(chat.username.toString(),Modifier.fillMaxWidth())
                }
            }
            DividerItem()
            Text(chat.chat.toString(),Modifier.padding(6.dp),textAlign=TextAlign.Justify)
        }
    }
}
@Composable
fun TradingCard(username:String,trading: Trading,editClick: () -> Unit,deleteClick: () -> Unit ){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(Modifier.padding(6.dp)) {

            HeaderCardItem("Date",trading.username.toString(),trading.clubName.toString())
            DividerItem()
            TwoTextItem("title",trading.title.toString())
            TwoTextItem("description", trading.desc.toString())
            Row(Modifier.fillMaxWidth(),horizontalArrangement =Arrangement.SpaceBetween){
                TwoTextItem("Buying", "${trading.amountBuying} ${trading.itemBuying}" )
                TwoTextItem("Selling", "${trading.amountSelling} ${trading.itemSelling}" )
            }
            if(username==trading.username) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    ButtonClickItem(desc = "Edit", onClick = editClick)
                    ButtonClickItem(desc = "Delete", onClick = deleteClick)
                }
            }
        }
    }
}
@Composable
fun TwoTextItem(text1:String,text2:String){
    Column() {
        Text(text1, style = MaterialTheme.typography.caption)
        Text(text2,textAlign = TextAlign.Justify)
    }
}
@Composable
fun HeaderCardItem(text1:String,text2: String,text3:String){
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            Text(text1,Modifier.fillMaxWidth())
        }
        Row(Modifier.weight(1f)) {
            Text(text2,Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        }
        Spacer(Modifier.padding(3.dp))
        Row(Modifier.weight(1f),) {
            Text(text3,Modifier.fillMaxWidth(),textAlign = TextAlign.End)
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
        val result= it.peekContent()
        when(result.status){
            Status.SUCCESS ->{
                Toast.makeText(
                    LocalContext.current,result.message ?: "Profile Updated", Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current,result.message ?: "An unknown error occured", Toast.LENGTH_SHORT
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
        WallCard(wall = it,onWall = {navController.navigate(BottomNavigationScreens.OtherProfile.withArgs(it.username.toString()))},onDelete = {
            authVM.isLoggedIn()
            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
            profileVM.deleteWall(it)})
 }
}
@Composable
fun SaveTodayDialog(today:Today){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val idState = remember { TextFieldState(today._id) }
        val regulerState = remember { TextFieldState(today.reguler) }
        val ultraState = remember { TextFieldState(today.ultra) }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "POTD") },
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
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "reguler", regulerState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "ultra", ultraState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveToday(
                        Today(
                            regulerState.text,
                            ultraState.text,
                            idState.text
                        )
                    )
                    homeVM.getToday()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false}
                    ){
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
@Composable
fun AddTradingDialog(trading: Trading?,onClick: () -> Unit={}){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val addVM = hiltViewModel<AddViewModel>()
        val titleState = remember { TextFieldState(trading?.title.toString()) }
        val descState= remember {TextFieldState(trading?.desc.toString())}
        val itemBuyingState = remember { TextFieldState(trading?.itemBuying.toString()) }
        val amountBuyingState = remember { TextFieldState(trading?.amountBuying.toString()) }
        val itemSellingState = remember { TextFieldState(trading?.itemSelling.toString()) }
        val amountSellingState = remember { TextFieldState(trading?.amountSelling.toString()) }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false
                    addVM.getAllTrading()
                    onClick
                                 },
                title = { Text(text = "Trading") },
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
                        TextFieldOutlined(desc = "Title", titleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Description", descState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Item Buying", itemBuyingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Amount Buying", amountBuyingState)
                        TextFieldOutlined(desc = "Item Selling", itemSellingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Amount Selling", amountSellingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    addVM.saveTrading(
                        Trading(
                            title=titleState.text,
                            desc = descState.text,
                            itemBuying = itemBuyingState.text,
                            amountBuying = amountBuyingState.text,
                            itemSelling = itemSellingState.text,
                            amountSelling= amountSellingState.text
                        )
                    )
                    addVM.getAllTrading()
                    onClick
                    openDialog= false
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false
                            addVM.getAllTrading()
                            onClick
                        }
                    ){
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
@Composable
fun SaveDropDialog(){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val idState = remember { TextFieldState() }
        val dayState= remember {TextFieldState()}
        val roleState = remember { TextFieldState() }
        val nameState = remember { TextFieldState() }
        val durationState = remember { TextFieldState() }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "Dropped") },
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
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "day", dayState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "role", roleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "name", nameState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "duration", durationState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveDrop(
                        Dropped(
                            roleState.text,
                            nameState.text,
                            durationState.text,
                            dayState.text,
                            idState.text
                        )
                    )
                    homeVM.getDropList()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            homeVM.deleteDrop(Dropped(
                                roleState.text,
                                nameState.text,
                                durationState.text,
                                dayState.text,
                                idState.text
                            ))
                            homeVM.getDropList()
                        }
                    ){
                        Text("Delete")
                    }
                }
            )
        }
    }
}
@Composable
fun SavePartyDialog(party:Party){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val idState = remember { TextFieldState(party._id) }
        val noState = remember { TextFieldState(party.no) }
        val roleState = remember { TextFieldState(party.role) }
        val nameState = remember { TextFieldState(party.name) }
        val durationState = remember { TextFieldState(party.duration) }
        val statusState = remember { TextFieldState(party.duration) }
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "Party") },
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
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "no", noState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "role", roleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "name", nameState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "duration", durationState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "status", statusState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveParty(
                        Party(
                            roleState.text,
                            noState.text,
                            nameState.text,
                            durationState.text,
                            statusState.text,
                            _id=idState.text
                        )
                    )
                    homeVM.getPartyList()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            homeVM.saveParty(
                                Party(
                                    roleState.text,
                                    noState.text,
                                    nameState.text,
                                    durationState.text,
                                    statusState.text,
                                    listOf(),
                                    listOf(),
                                    listOf(),
                                    idState.text
                                )
                            )
                            homeVM.getPartyList()
                            openDialog= false
                        }
                    ){
                        Text("Awal")
                    }
                }
            )
        }
    }
}
@Composable
fun ObserveUserList(onClick: () -> Unit){
    val homeVM = hiltViewModel<HomeViewModel>()
    var userList = listOf<User>()
    val listUserState = homeVM.listUserStatus.observeAsState()
    listUserState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                userList= it.data ?: return@let
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, it.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            userList.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(12.dp),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        Arrangement.SpaceBetween
                    ){
                        TwoTextItem(text1 = it.username, text2 = it.name.toString())
                        TwoTextItem(text1 = it.ign.toString(), text2 = it.clubName.toString())
                    }
                }
            }
            ButtonClickItem(desc = "Close", onClick = onClick)
        }
    }
}
@Composable
fun SearchRefreshItem(desc: String,state: TextFieldState=remember{ TextFieldState() },onClick: () -> Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),Arrangement.Center,Alignment.CenterVertically){
        OutlinedTextField(
            label={Text(text=desc)},
            value =state.text,
            onValueChange = {
                state.text = it
            }
        )
        Spacer(Modifier.padding(6.dp))
        Image(
            painterResource(id = R.drawable.search),
            contentDescription = "Search Menu",
            modifier= Modifier
                .height(24.dp)
                .clickable {}
        )
        Spacer(Modifier.padding(6.dp))
        Image(
            painterResource(id = R.drawable.refresh),
            contentDescription = "Search Menu",
            modifier= Modifier
                .height(24.dp)
                .clickable {}
        )

    }
}
