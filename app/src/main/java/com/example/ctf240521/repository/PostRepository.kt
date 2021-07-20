package com.example.ctf240521.repository

import android.app.Application
import androidx.room.Update
import com.example.ctf240521.data.local.PostDao
import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.data.remote.PostApi
import com.example.ctf240521.data.remote.requests.AccountRequest
import com.example.ctf240521.data.remote.requests.OneRequest
import com.example.ctf240521.data.remote.requests.UpdateUserRequest
import com.example.ctf240521.util.Event
import com.example.ctf240521.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postDao: PostDao,
    private val postApi: PostApi,
    private val context: Application
) {
    suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = postApi.login(AccountRequest(email, password))
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()?.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }

    suspend fun register(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = postApi.register(AccountRequest(email, password))
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()?.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun getUser(username: String)= withContext(Dispatchers.IO){
        try {
            val response= postApi.getUser(OneRequest(property = username))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun updateProfile(updateUserReq: UpdateUserRequest) = withContext(Dispatchers.IO) {
        try {
            val response = postApi.updateProfile(updateUserReq)
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()!!.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }

    suspend fun getAllPosts() = withContext(Dispatchers.IO) {
        try {
            val response = postApi.getAllPosts()

            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), null)
            }
        }catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }

    }

    suspend fun getFollowingPosts(username:String)= withContext(Dispatchers.IO){
        try {
            val response = postApi.getFollowingPosts(username)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e: Exception) {
            Resource.error("Couldnt connect to the server, check your internet connection", null)
        }
    }
}