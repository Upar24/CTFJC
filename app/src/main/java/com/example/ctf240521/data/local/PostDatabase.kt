package com.example.ctf240521.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ctf240521.data.local.entities.Post

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PostDatabase : RoomDatabase(){
    abstract fun postDao() : PostDao
}