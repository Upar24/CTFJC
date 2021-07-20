package com.example.ctf240521.util


object Constants{
    const val DATABASE_NAME = "ctf_db"
    const val BASE_URL = "http://10.0.2.2:8080"
    const val NO_USERNAME = "NO_USERNAME"
    const val NO_PASSWORD = "NO_PASSWORD"
    const val KEY_LOGGED_IN_USERNAME = "KEY_LOGGED_IN_USERNAME"
    const val KEY_LOGGED_IN_PASSWORD = "KEY_LOGGED_IN_PASSWORD"
    const val LOGIN = "Login"
    const val LOGOUT = "Logout"
    const val ENCRYPTED_SHARED_PREF_NAME = "enc_shared_pref"
    val IGNORE_AUTH_URLS = listOf("/login","/register","/getuser")
}
