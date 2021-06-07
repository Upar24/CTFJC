package com.example.ctf240521.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="post")
data class Post(
    val title:String,
    val desc:String,
    val date: Long,
    val comment: List<String> =listOf("="),
    @PrimaryKey(autoGenerate = false)
    val _id:String= UUID.randomUUID().toString()
)
