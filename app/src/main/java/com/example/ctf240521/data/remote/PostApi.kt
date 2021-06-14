package com.example.ctf240521.data.remote

import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.data.remote.requests.AccountRequest
import com.example.ctf240521.data.remote.requests.AddCommentRequest
import com.example.ctf240521.data.remote.requests.IDRequest
import com.example.ctf240521.data.remote.response.SimpleResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApi {

    @POST("/register")
    suspend fun register(
        @Body registerRequest: AccountRequest
    ):Response<SimpleResponse>

    @POST("/login")
    suspend fun login(
        @Body loginRequest: AccountRequest
    ):Response<SimpleResponse>

    @POST("/addPost")
    suspend fun addPost(
        @Body post: Post
    ):Response<ResponseBody>

    @POST("/deletePost")
    suspend fun deletePost(
        @Body deleteNoteRequest: IDRequest
    ):Response<ResponseBody>

    @POST("/addCommentToPost")
    suspend fun addCommentToPost(
        @Body addCommentRequest: AddCommentRequest
    ):Response<SimpleResponse>

    @GET("/getAllPosts")
    suspend fun getAllPosts():Response<List<Post>>

    @GET("/getFollowingPosts")
    suspend fun getFollowingPosts(username:String):Response<List<Post>>
}













