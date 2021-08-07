package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ctf240521.data.local.entities.Dropped
import com.example.ctf240521.data.local.entities.Today
import com.example.ctf240521.data.local.entities.Party
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.ui.screens.home.HomeViewModel
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.NA
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Status
import com.example.ctf240521.util.listString.all
import com.example.ctf240521.util.listString.anime
import com.example.ctf240521.util.listString.anime7
import com.example.ctf240521.util.listString.arts
import com.example.ctf240521.util.listString.arts7
import com.example.ctf240521.util.listString.dropped
import com.example.ctf240521.util.listString.hpstr
import com.example.ctf240521.util.listString.hpstr7
import com.example.ctf240521.util.listString.nope
import com.example.ctf240521.util.listString.thebc
import com.example.ctf240521.util.listString.thebc7
import com.example.ctf240521.util.listString.vipp
import com.example.ctf240521.util.listString.vipp7
import com.example.ctf240521.viewmodel.AuthViewModel

@Composable
fun PartyScreen() {
    val homeVM = hiltViewModel<HomeViewModel>()
    var today = Today("", "", "")
    val regulerDropList = mutableListOf<Dropped>()
    val ultraDropList = mutableListOf<Dropped>()
    val listParty = listOf(arts,thebc,hpstr,vipp,anime,arts7,thebc7,hpstr7,vipp7,anime7,dropped,nope,all)
    val artsList = mutableListOf<Party>()
    val thebcList = mutableListOf<Party>()
    val hpstrList = mutableListOf<Party>()
    val vippList = mutableListOf<Party>()
    val animeList = mutableListOf<Party>()
    val arts7List = mutableListOf<Party>()
    val thebc7List = mutableListOf<Party>()
    val hpstr7List = mutableListOf<Party>()
    val vipp7List = mutableListOf<Party>()
    val anime7List = mutableListOf<Party>()
    val droppedList = mutableListOf<Party>()
    val nopeList = mutableListOf<Party>()
    val allList = mutableListOf<Party>()
    var visibleParty by remember { mutableStateOf(arts) }
    var saveTodayDialog by remember { mutableStateOf(false) }
    var saveDropDialog by remember { mutableStateOf(false) }
    var savePartyDialog by remember { mutableStateOf(false) }
    var userListDialog by remember { mutableStateOf(false) }
    val savePartyState = homeVM.savePartyStatus.observeAsState()
    savePartyState.value?.let {
        val result= it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "Party saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val partyListState = homeVM.partyListStatus.observeAsState()
    partyListState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.forEach {party->
                    if(party.role == arts && party.status == NA){
                        artsList.add(party)
                    }else if(party.role == thebc && party.status ==NA){
                        thebcList.add(party)
                    }else if(party.role == hpstr && party.status == NA){
                        hpstrList.add(party)
                    }else if(party.role == vipp && party.status == NA){
                        vippList.add(party)
                    }else if(party.role== anime && party.status == NA){
                        animeList.add(party)
                    }else if(party.role==arts7 && party.status == NA){
                        arts7List.add(party)
                    }else if(party.role== thebc7 && party.status ==NA){
                        thebc7List.add(party)
                    }else if(party.role== hpstr7 && party.status==NA){
                        hpstr7List.add(party)
                    }else if(party.role==vipp7 && party.status == NA){
                        vipp7List.add(party)
                    }else if(party.role==anime7 && party.status == NA){
                        anime7List.add(party)
                    }else if(party.status != nope && party.status != NA){
                        droppedList.add(party)
                    }else{
                        nopeList.add(party)
                    }
                }
                it.data?.forEach {party ->
                    allList.add(party)
                }
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
    val saveDropState = homeVM.saveDropStatus.observeAsState()
    saveDropState.value?.let {
        val result = it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "Drop party saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val dropListState = homeVM.dropListStatus.observeAsState()
    dropListState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.forEach {dropped ->
                    if(dropped.role == "reguler"){
                        regulerDropList.add(dropped)
                    }else{
                        ultraDropList.add(dropped)
                    }
                }
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
    val saveTodayState = homeVM.saveTodayStatus.observeAsState()
    saveTodayState.value?.let {
        val result= it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "Today saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val todayState = homeVM.todayStatus.observeAsState()
    todayState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                today = it.data ?: return@let
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
    val deleteDropState = homeVM.deleteDropStatus.observeAsState()
    deleteDropState.value?.let {
        val result =it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "Drop party deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    Column(Modifier.padding(start = 3.dp, end = 3.dp)) {
        if (saveTodayDialog) SaveTodayDialog(today)
        if (saveDropDialog) SaveDropDialog()
        if (savePartyDialog) SavePartyDialog(Party(role= visibleParty,"","",NA))
        if (userListDialog) ObserveUserList({userListDialog= !userListDialog})
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            val (spacerText, regulerText, listReguler, ultraText, listUltra) = createRefs()

            Spacer(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .constrainAs(spacerText) {
                        centerVerticallyTo(regulerText)
                        centerHorizontallyTo(parent)
                        height = Dimension.fillToConstraints
                    }
            )
            Text(
                "Today Reguler: ${today.reguler}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(regulerText) {
                        start.linkTo(parent.start)
                        bottom.linkTo(listReguler.top)
                    }
                    .clickable { saveTodayDialog = !saveTodayDialog }
            )
            Text(
                "Today Ultra: ${today.ultra}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(ultraText) {
                        start.linkTo(spacerText.end)
                        bottom.linkTo(listUltra.top)
                    }
                    .clickable { saveDropDialog = !saveDropDialog })
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .horizontalScroll(rememberScrollState())
                    .constrainAs(listReguler) {
                        start.linkTo(parent.start)
                        end.linkTo(spacerText.start)
                    }
            ) {
                regulerDropList.forEach {
                    CardDrop(it)
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                }
            }
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .horizontalScroll(rememberScrollState())
                    .constrainAs(listUltra) {
                        start.linkTo(spacerText.end)
                        end.linkTo(parent.end)
                    }
            ) {
                ultraDropList.forEach {
                    CardDrop(it)
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        var tabIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(
            selectedTabIndex = tabIndex, modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent
        ) {
            listParty.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    visibleParty = text
                    savePartyDialog = !savePartyDialog
                }, text = {
                    Text(
                        text,
                        modifier = Modifier
                            .padding(6.dp),
                        style = if (visibleParty == text) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                        color = if (visibleParty == text) Color.Magenta else colors.onBackground
                    )
                })
            }
        }
        Spacer(modifier = Modifier.padding(3.dp))
        val listDisplay:MutableList<Party> = when(visibleParty){
            arts -> artsList
            thebc -> thebcList
            hpstr -> hpstrList
            vipp -> vippList
            anime -> animeList
            arts7 -> arts7List
            thebc7 -> thebc7List
            hpstr7 -> hpstr7List
            vipp7 -> vipp7List
            anime7 -> anime7List
            dropped -> droppedList
            nope -> nopeList
            else -> allList
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {

            listDisplay.forEach { party->
                Card(
                    Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp,colors.onSurface),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor =colors.secondary
                ) {
                    var editPartyDialog by remember { mutableStateOf(false) }
                    if(editPartyDialog) SavePartyDialog(party = party)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { editPartyDialog = !editPartyDialog }
                            .padding(start = 6.dp, end = 6.dp, top = 3.dp, bottom = 3.dp)
                    ) {
                        val authVM = hiltViewModel<AuthViewModel>()
                        val username = authVM.sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME
                        ) ?: NO_USERNAME
                        authVM.isLoggedIn()
                        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                        val check by remember { mutableStateOf(username in party.check) }
                        val nope by remember { mutableStateOf(username in party.nope) }
                        val drop by remember { mutableStateOf(username in party.drop) }
                        val countChecked by remember{ mutableStateOf(party.check.size)}
                        val countNope by remember { mutableStateOf(party.nope.size)}
                        val countDropped by remember { mutableStateOf(party.drop.size)}
                        val status by remember { mutableStateOf("lmao")}
                        Row(Modifier.fillMaxWidth(0.2f), Arrangement.Start) {
                            ProfileInfoItem(number = party.name, desc = party.duration)
                        }
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(party.status)
                                Text(status)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(check){
                                    Text(countChecked.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.check)
                                            userListDialog = !userListDialog},Color.Yellow)
                                    Text("Checked",Modifier.clickable {
                                        homeVM.toggleCheck(party)},Color.Yellow)
                                }else{
                                    Text(countChecked.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.check)
                                            userListDialog = !userListDialog})
                                    Text("Check",Modifier.clickable {
                                        homeVM.toggleCheck(party)
                                    })
                                }
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(nope){
                                    Text(countNope.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.nope)
                                            userListDialog = !userListDialog},Color.Red)
                                    Text("Nope",Modifier.clickable {
                                        homeVM.toggleNope(party)},Color.Red)
                                }else{
                                    Text(countNope.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.nope)
                                            userListDialog = !userListDialog})
                                    Text("Nope",Modifier.clickable {
                                        homeVM.toggleNope(party)},)
                                }
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(drop){

                                    Text(countDropped.toString(),
                                        Modifier
                                            .clickable{homeVM.getListUser(party.drop)
                                                userListDialog = !userListDialog},Color.Green)
                                    Text("Dropped",Modifier.clickable {
                                        homeVM.toggleDrop(party)},Color.Green)
                                }else{
                                    Text(countDropped.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.drop)
                                            userListDialog = !userListDialog})
                                    Text("Drop",Modifier.clickable {
                                        homeVM.toggleDrop(party) },)
                                }
                            }
                        }
                    }
                Spacer(modifier = Modifier.padding(3.dp))
            }
        }
            ButtonClickItem(desc ="click", onClick = {homeVM.getPartyList()})
    }
}
}