package com.example.ctf240521.util

open class Event <out T>(private val content: T){
    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled()= if(hasBeenHandled){
        null
    }else{
        hasBeenHandled = true
        content
    }
    fun peekContent() = content
}