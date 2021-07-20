package com.example.ctf240521.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf240521.data.local.entities.User
import com.example.ctf240521.data.remote.requests.UpdateUserRequest
import com.example.ctf240521.repository.PostRepository
import com.example.ctf240521.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PostRepository
): ViewModel(){
    private val _user = MutableLiveData<Resource<User>>()
    val user : LiveData<Resource<User>> = _user
    private val _updateProfile =MutableLiveData<Resource<String>>()
    val updateProfile : LiveData<Resource<String>> = _updateProfile

    fun getUser(username:String){
        _user.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result=repository.getUser(username)
            _user.postValue(result)
        }
    }

    fun updateProfile(updateUserReq:UpdateUserRequest){
        _updateProfile.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result=repository.updateProfile(updateUserReq)
            _updateProfile.postValue(result)
        }
    }

}