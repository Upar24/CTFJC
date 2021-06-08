package com.example.ctf240521.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.repository.PostRepository
import com.example.ctf240521.ui.component.PasswordField
import com.example.ctf240521.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val repository: PostRepository
):ViewModel(){
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
    viewModelScope.launch{
        val result= repository.register(username,password)
        _registerStatus.postValue(result)
    }
    }
}
class TextFieldState(){
    var text : String by mutableStateOf("")
}




















