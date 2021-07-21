package com.example.ctf240521.data.local.entities

import androidx.room.PrimaryKey
import java.util.*

data class Chat(
    val username: String?="",
    val name:String?="",
    val clubName:String?="",
    var chat:String?="",
    var date: Long,
    val type:String?=null,
    @PrimaryKey(autoGenerate = false)
    val _id:String= UUID.randomUUID().toString()
)
data class Wall(
    val username: String? ="",
    val name:String?="",
    val clubName:String?="",
    val wallOwner:String?="",
    var chat:String?="",
    var date: Long,
    @PrimaryKey(autoGenerate = false)
    val _id:String= UUID.randomUUID().toString()
)