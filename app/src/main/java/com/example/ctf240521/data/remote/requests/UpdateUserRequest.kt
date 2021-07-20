package com.example.ctf240521.data.remote.requests

data class UpdateUserRequest(
    val name:String?="",
    val clubName:String?="",
    val ign:String?="",
    val bio:String?=""
)