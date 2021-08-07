package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ctf240521.data.local.entities.Trading
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.ui.screens.add.AddViewModel
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Status
import com.example.ctf240521.util.listString.all
import com.example.ctf240521.util.listString.buying
import com.example.ctf240521.util.listString.search
import com.example.ctf240521.util.listString.selling
import com.example.ctf240521.viewmodel.AuthViewModel

@Composable
fun AddScreen(){
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp)
    ) {
        AddTradingDialog(Trading("","","","","","","","",""))
        val addVM = hiltViewModel<AddViewModel>()
        val authVM= hiltViewModel<AuthViewModel>()
        val username = authVM.sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME
        var visibleScreen by remember { mutableStateOf(buying) }
        val queryState = remember { TextFieldState() }
        val allTradingList = mutableListOf<Trading>()
        val buyingTradingList = mutableListOf<Trading>()
        val sellingTradingList = mutableListOf<Trading>()
        var trading by remember{mutableStateOf(Trading("","",""))}
        val saveState= addVM.saveStatus.observeAsState()
        saveState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS -> {Toast.makeText(
                    LocalContext.current,result.message ?: "trading  saved",
                    Toast.LENGTH_SHORT).show()
                    addVM.getAllTrading()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val deleteState= addVM.deleteStatus.observeAsState()
        deleteState.value?.let {
            val result= it.peekContent()
            when(result.status){
                Status.SUCCESS -> {}
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val tradingState= addVM.tradingStatus.observeAsState()
        tradingState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {
                        trading = it
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val allTradingState= addVM.allTradingStatus.observeAsState()
        allTradingState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.forEach {
                        allTradingList.add(it)
                        buyingTradingList.add(it)
                        sellingTradingList.add(it)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val buyingSearchState= addVM.buyingSearchStatus.observeAsState()
        buyingSearchState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.forEach {
                        buyingTradingList.add(it)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val sellingSearchState= addVM.sellingSearchStatus.observeAsState()
        sellingSearchState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.forEach {
                        sellingTradingList.add(it)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured",
                        Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        TextFieldOutlined(desc = search,queryState)
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){
            ButtonClickItem(
                desc = buying, onClick = {
                    allTradingList.clear()
                    buyingTradingList.clear()
                    sellingTradingList.clear()
                    visibleScreen = buying
                    if(queryState.text.isEmpty()){
                        addVM.getAllTrading()}
                    else{
                        addVM.getBuyingSearch(queryState.text)
                    }
                }
            )
            ButtonClickItem(
                desc = all, onClick = {
                    allTradingList.clear()
                    buyingTradingList.clear()
                    sellingTradingList.clear()
                    visibleScreen = all
                    addVM.getAllTrading()
                }
            )
            ButtonClickItem(
                desc =selling, onClick = {
                    allTradingList.clear()
                    buyingTradingList.clear()
                    sellingTradingList.clear()
                    visibleScreen = selling
                    if(queryState.text.isEmpty()){
                        addVM.getAllTrading()
                    }
                    else{
                        addVM.getSellingSearch(queryState.text) }
                    }
            )
        }
        val listDisplay:MutableList<Trading> = when (visibleScreen){
            buying -> buyingTradingList
            selling -> sellingTradingList
            else -> allTradingList
        }
        Column (
            Modifier.verticalScroll(rememberScrollState())
        ){
            listDisplay.forEach { trading1 ->
                Card(
                    Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ){
                    var opendDialog by remember { mutableStateOf(false)}
                    if(opendDialog) AddTradingDialog(trading = trading1)
                    TradingCard(username,trading1,
                        editClick= {
                            trading = trading1
                            opendDialog = !opendDialog
                        },
                        deleteClick = {
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            addVM.deleteTrading(trading1)
                        }
                    )
                }
            }
        }

//        var openDialog by remember { mutableStateOf(false)}
//        if(visibleScreen==buying){
//            Text(buying)
//            buyingTradingList.forEach {
//                TradingCard(username,it,
//                    editClick= {
//                        trading = it
//                        openDialog =! openDialog
//                    },
//                    deleteClick = {
//                        authVM.isLoggedIn()
//                        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
//                        addVM.deleteTrading(it)
//                    }
//                )
//                Text("buying")
//            }
//        }else if(visibleScreen==selling){
//            Text(selling)
//            sellingTradingList.forEach {
//                TradingCard(username,it,
//                    editClick= {
//                        trading=it
//                        openDialog=!openDialog
//                    },
//                    deleteClick = {
//                        authVM.isLoggedIn()
//                        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
//                        addVM.deleteTrading(it)
//                    }
//                )
//            }
//            Text("selling")
//        }else{
//            Text(all)
//            allTradingList.forEach{
//                TradingCard(username,it,
//                    editClick= {
//                        trading = it
//                        openDialog=!openDialog
//                    },
//                    deleteClick = {
//                        authVM.isLoggedIn()
//                        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
//                        addVM.deleteTrading(it)
//                    }
//                )
//                Text("all")
//            }
//        }

    }

}