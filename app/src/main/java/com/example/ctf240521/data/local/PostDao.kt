package com.example.ctf240521.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ctf240521.data.local.entities.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post:Post)

    @Query("DELETE FROM post WHERE _id = :postID")
    suspend fun deletePostById(postID:String)

    @Query("SELECT * FROM post WHERE _id= :postID")
    fun observePostById(postID: String): LiveData<Post>

    @Query("SELECT * FROM post WHERE _id = :postID")
    suspend fun getPostById(postID: String): Post?

    @Query("SELECT * FROM post ORDER BY date DESC")
    fun getAllPosts(): Flow<List<Post>>
}
























