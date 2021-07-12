package com.example.ctf240521.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.ctf240521.ui.component.CardParty
import com.example.ctf240521.ui.component.ProfileInfoItem

@Composable
fun PartyScreen(){
    val regulerList = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20")
    val ultraList = listOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t")
    val listParty = listOf("Party1","Party2","Party3","Party4","Party5","Party6","Party7","Party8",)
    var visibleParty by remember{ mutableStateOf("Party1")}
    Column(Modifier.padding(start = 3.dp,end = 3.dp)) {
        ConstraintLayout(modifier= Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)){
            val (spacerText,regulerText,listReguler,ultraText,listUltra)=createRefs()

            Spacer(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .constrainAs(spacerText) {
                        centerVerticallyTo(regulerText)
                        centerHorizontallyTo(parent)
                        height = Dimension.fillToConstraints
                    }
            )
            Text("Today Reguler: CC",style = MaterialTheme.typography.body2,modifier=Modifier.constrainAs(regulerText){
                start.linkTo(parent.start)
                bottom.linkTo(listReguler.top)
            })
            Text("Today Ultra: CC",style = MaterialTheme.typography.body2,modifier=Modifier.constrainAs(ultraText){
                start.linkTo(spacerText.end)
                bottom.linkTo(listUltra.top)
            })
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .horizontalScroll(rememberScrollState())
                    .constrainAs(listReguler) {
                        start.linkTo(parent.start)
                        end.linkTo(spacerText.start)
                    }
            ){
                val list= regulerList.asReversed()
                list.forEach {
                    CardParty(desc = "Day $it")
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
            ){
                val list=ultraList.asReversed()
                list.forEach {
                    CardParty(desc = "Day $it")
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(1f),
            shape= RoundedCornerShape(24.dp),
            border= BorderStroke(3.dp,colors.surface),
            backgroundColor = Color.Transparent
        ){
            Row(Modifier.horizontalScroll(rememberScrollState()),Arrangement.Center) {
                listParty.forEach{
                    Text(
                        text = it,
                        Modifier
                            .padding(6.dp)
                            .clickable { visibleParty = it },
                        style = if(visibleParty==it)MaterialTheme.typography.button else MaterialTheme.typography.body1,
                        color=if(visibleParty==it)Color.Magenta else colors.onBackground
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(3.dp))
        Column(Modifier.verticalScroll(rememberScrollState())) {
            regulerList.forEach{
                Card(
                    Modifier.fillMaxWidth(),
                    border=BorderStroke(1.dp,colors.onSurface),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = colors.secondary
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp, end = 6.dp, top = 3.dp, bottom = 3.dp)) {
                        Row(Modifier.fillMaxWidth(0.2f),Arrangement.Start) {
                            ProfileInfoItem(number = it, desc ="4 hrs" )
                        }
                        Row(Modifier.fillMaxWidth(),Arrangement.SpaceEvenly){
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("N/A")
                                Text("Status")
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Text("24")
                                Text("Checked")
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Text("12")
                                Text("Nope")
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally){
                                Text("96")
                                Text("Dropped")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(3.dp))
            }
        }
    }
}
//@Preview
//@Composable
//fun X(){
//    PartyScreen()
//}