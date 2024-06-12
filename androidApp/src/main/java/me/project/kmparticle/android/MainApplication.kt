package me.project.kmparticle.android

import android.app.Application
import me.project.kmparticle.android.di.databaseModule
import me.project.kmparticle.android.di.viewModelModule
import me.project.kmparticle.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        val modules = sharedModule + viewModelModule + databaseModule
        startKoin {
            androidContext(this@MainApplication)
            modules(modules)
        }
    }
}