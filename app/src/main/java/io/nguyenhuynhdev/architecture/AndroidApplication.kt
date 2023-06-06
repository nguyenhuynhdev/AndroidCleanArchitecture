package io.nguyenhuynhdev.architecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}