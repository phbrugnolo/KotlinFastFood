package com.example.fastfooda1

import android.app.Application
import com.example.fastfooda1.database.AppContainer
import com.example.fastfooda1.database.AppDataContainer

class FastFoodApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}