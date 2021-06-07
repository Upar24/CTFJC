package com.example.ctf240521.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.ctf240521.data.local.PostDatabase
import com.example.ctf240521.data.remote.BasicAuthInterceptor
import com.example.ctf240521.data.remote.PostApi
import com.example.ctf240521.util.Constants.BASE_URL
import com.example.ctf240521.util.Constants.DATABASE_NAME
import com.example.ctf240521.util.Constants.ENCRYPTED_SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePostDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(context, PostDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePostDao(db: PostDatabase) = db.postDao()

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor()= BasicAuthInterceptor()

    @Singleton
    @Provides
    fun providePostApi(
        basicAuthInterceptor: BasicAuthInterceptor
    ) : PostApi {
        val client= OkHttpClient.Builder()
            .addInterceptor(basicAuthInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PostApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey= MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}















