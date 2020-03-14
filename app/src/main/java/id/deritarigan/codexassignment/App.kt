package id.deritarigan.codexassignment

import android.app.Application
import id.deritarigan.codexassignment.di.NetworkModule
import id.deritarigan.codexassignment.di.ViewModelsModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            this@App
        }.modules(
            listOf<Module>(
                NetworkModule.module,
                ViewModelsModule.module
            )
        )
    }
}