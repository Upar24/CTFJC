package com.example.ctf240521.ui.screens.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.repository.PostRepository
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Event
import com.example.ctf240521.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor (
    private val repository: PostRepository
): ViewModel(){
    private val _posts = MutableLiveData<Event<Resource<List<Post>>>>()
    val post : LiveData<Event<Resource<List<Post>>>> = _posts

    fun getAllPosts(){
        _posts.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result= repository.getAllPosts()
            _posts.postValue(Event(result))
        }
    }
    fun getFollowingPosts(username:String){
        if(username != NO_USERNAME){
            _posts.postValue(Event(Resource.loading(null)))
            viewModelScope.launch {
                val result= repository.getFollowingPosts(username)
                _posts.postValue(Event(result))
            }
        }else{
            _posts.postValue(Event(Resource.loading(null)))
            _posts.postValue(Event(Resource.error("Please Log in",null)))
        }
    }
}

