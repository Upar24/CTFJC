package com.example.ctf240521.data.remote

import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.data.local.entities.User
import com.example.ctf240521.data.local.entities.Wall
import com.example.ctf240521.data.remote.requests.*
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

    @POST("/getuser")
    suspend fun getUser(
        @Body oneRequest: OneRequest
    ):Response<User>

    @POST("updateuser")
    suspend fun updateProfile(
        @Body updateUserReq: UpdateUserRequest
    ):Response<SimpleResponse>

    @POST("/savewall")
    suspend fun saveWall(
        @Body wallRequest: WallRequest
    ):Response<SimpleResponse>

    @POST("/getwall")
    suspend fun getWall(
        @Body getRequest : OneRequest
    ):Response<List<Wall>>

    @POST("/deletewall")
    suspend fun deleteWall(
        @Body wall:Wall
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













