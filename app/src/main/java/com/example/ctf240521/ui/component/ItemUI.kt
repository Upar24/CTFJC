package com.example.ctf240521.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctf240521.R

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
fun TextFieldOutlined(desc:String,state: TextFieldState = remember {TextFieldState()},modifier: Modifier=Modifier.height(36.dp)){
    OutlinedTextField(
        label={Text(text=desc)},
        value =state.text,
        onValueChange = {
            state.text = it
        },
        modifier=modifier
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
        verticalAlignment =Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
            Spacer(Modifier.size(10.dp))
            Text(
                text="Please wait.."
            )
        }

    }
}
@Composable
fun ProgressCardToastItem(){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(10.dp),
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
                    .clickable(onClick = onIconClick)
            )
            Spacer(modifier = Modifier.padding(6.dp))
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
fun WallCard() {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text("Username")
                Text("Date")
            }
            DividerItem()
            Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween) {
                Text("Description to their message")
                ButtonClickItem(desc = "Wall", onClick = {})
            }
        }
    }
}
@Preview
@Composable
fun x(){
    WallCard()
}
