package com.example.ctf240521.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.data.remote.BasicAuthInterceptor
import com.example.ctf240521.repository.PostRepository
import com.example.ctf240521.util.Constants
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.LOGIN
import com.example.ctf240521.util.Constants.LOGOUT
import com.example.ctf240521.util.Constants.NO_PASSWORD
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Event
import com.example.ctf240521.util.Resource
import com.example.ctf240521.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val repository: PostRepository
):ViewModel(){
    @Inject
    lateinit var sharedPref: SharedPreferences
    @Inject
    lateinit var basicAuthInterceptor: BasicAuthInterceptor
    var usernamevm: String?= null
    var passwordvm: String?= null


    private val _registerStatus = MutableLiveData<Resource<String>>()
    val registerStatus : LiveData<Resource<String>> = _registerStatus
    private val _loginStatus = MutableLiveData<Resource<String>>()
    val loginStatus : LiveData<Resource<String>> = _loginStatus


    fun loginUser(username:String,password:String){
        _loginStatus.postValue(Resource.loading(null))
        if(username.isEmpty() || password.isEmpty()){
            _loginStatus.postValue(Resource.error("Please fill out all the fields",null))
            return
        }
        viewModelScope.launch{
            usernamevm=username
            passwordvm=password
            val result= repository.login(username,password)
            _loginStatus.postValue(result)
        }
    }

    fun registerUser(username:String,password:String,repeatedPassword:String){
        _registerStatus.postValue(Resource.loading(null))
        if(username.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()){
            _registerStatus.postValue(Resource.error("Please fill out all the fields",null))
            return
        }
        if(password != repeatedPassword){
            _registerStatus.postValue(Resource.error("The passwords do not match", null))
            return
        }
        if(username.length < 3 || password.length < 3){
            _registerStatus.postValue(Resource.error("must be at least 3 characters.",null))
            return
        }
        if(username.length > 24 || password.length > 24){
            _registerStatus.postValue(Resource.error("characters are too long.",null))
            return
        }
        viewModelScope.launch {
            val result = repository.register(username, password)
            _registerStatus.postValue(result)
        }
    }

    fun isLoggedIn():Boolean{
        usernamevm=sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME
        passwordvm=sharedPref.getString(KEY_LOGGED_IN_PASSWORD, NO_PASSWORD) ?: NO_PASSWORD
        return usernamevm!= NO_USERNAME && passwordvm != NO_PASSWORD
    }
    fun authenticateApi(username:String, password: String){
        basicAuthInterceptor.username=username
        basicAuthInterceptor.password=password
    }

    private val _desc= MutableStateFlow("")
    var desc: StateFlow<String> = _desc
    fun getDesc()=viewModelScope.launch{
        isLoggedIn()
        val username =if(sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) == NO_USERNAME) LOGIN else LOGOUT
        _desc.value=username
    }
}




















