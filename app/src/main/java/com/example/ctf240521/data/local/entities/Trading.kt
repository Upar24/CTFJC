package com.example.ctf240521.data.local.entities

import androidx.room.PrimaryKey
import java.util.*

data class Trading(
    val username:String?="",
    val name:String?="",
    val clubName:String?="",
    val title:String?="",
    val desc:String?="",
    val itemBuying:String?="",
    val amountBuying:String?="",
    val itemSelling:String?="",
    val amountSelling:String?="",
    val date:Long=0,
    @PrimaryKey(autoGenerate = false)
    val _id:String= UUID.randomUUID().toString()

)