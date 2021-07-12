package com.example.ctf240521

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.example.ctf240521.ui.theme.CTF240521Theme
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}