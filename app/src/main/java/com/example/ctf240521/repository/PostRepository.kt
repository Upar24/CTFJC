package com.example.ctf240521.repository

import android.app.Application
import androidx.room.Update
import com.example.ctf240521.data.local.PostDao
import com.example.ctf240521.data.local.entities.*
import com.example.ctf240521.data.remote.PostApi
import com.example.ctf240521.data.remote.requests.*
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

    suspend fun saveWall(wall: Wall) = withContext(Dispatchers.IO){
        try{
            val response = postApi.saveWall(wall)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getWall(username: String)= withContext(Dispatchers.IO){
        try {
            val response=postApi.getWall(OneRequest(username))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else {
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun deleteWall(wall: Wall)= withContext(Dispatchers.IO){
        try {
            val response=postApi.deleteWall(wall)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveChat(chat: Chat)= withContext(Dispatchers.IO){
        try {
            val response = postApi.saveChat(chat)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getChat()= withContext(Dispatchers.IO){
        try {
            val response= postApi.getChat()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveTrading(trading: Trading)= withContext(Dispatchers.IO) {
        try {
            val response = postApi.saveTrading(trading)
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()!!.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun deleteTrading(trading: Trading)= withContext(Dispatchers.IO){
        try {
            val response=postApi.deleteTrading(trading)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getAllTrading()= withContext(Dispatchers.IO){
        try {
            val response= postApi.getAllTrading()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getAllUserTrading(query:String)= withContext(Dispatchers.IO){
        try {
            val response=postApi.getAllUserTrading(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getTrading(trading: Trading)= withContext(Dispatchers.IO){
        try {
            val response=postApi.getTrading(trading)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getBuyingSearch(query:String)= withContext(Dispatchers.IO){
        try {
            val response=postApi.getBuyingSearch(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getSellingSearch(query:String)= withContext(Dispatchers.IO){
        try {
            val response=postApi.getSellingSearch(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveParty(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=postApi.saveParty( party)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getPartyList()= withContext(Dispatchers.IO){
        try {
            val response= postApi.getPartyList()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveDrop(dropped: Dropped)= withContext(Dispatchers.IO){
        try {
            val response=postApi.saveDrop( dropped)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getDropList()= withContext(Dispatchers.IO){
        try {
            val response= postApi.getDrop()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveToday(today: Today)= withContext(Dispatchers.IO){
        try {
            val response=postApi.saveToday(today)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getToday()= withContext(Dispatchers.IO){
        try {
            val response= postApi.getToday()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun deleteDrop(dropped: Dropped)= withContext(Dispatchers.IO){
        try {
            val response=postApi.deleteDrop(dropped)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }  }
    suspend fun toggleCheck(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=postApi.toggleCheck(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun toggleDrop(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=postApi.toggleDrop(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun toggleNope(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=postApi.toggleNope(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getListUser(listString:List<String>)= withContext(Dispatchers.IO){
        try{
            val response=postApi.getListUser(ListStringRequest(listString))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
}