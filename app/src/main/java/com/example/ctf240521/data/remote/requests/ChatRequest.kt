package com.example.ctf240521.data.remote.requests

data class ChatRequest(
    val chat:String?=null,
    val type:String?=null
)
data class WallRequest(
    val chat:String?="",
    val wallOwner:String?=""
)