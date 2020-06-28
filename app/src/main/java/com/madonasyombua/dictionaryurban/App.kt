package com.madonasyombua.dictionaryurban

import android.app.Application
import com.madonasyombua.dictionaryurban.di.apiModule
import com.madonasyombua.dictionaryurban.di.repositoryModule
import com.madonasyombua.dictionaryurban.di.viewModelModule
import com.madonasyombua.dictionaryurban.di.wordDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    //startKoin: It is the starting point. This method receives a list of “modules”
    override fun onCreate() {
        super.onCreate()
        //plant Timber for logging only on debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            // injects Android context
            androidContext(this@App)
            // load your module
            modules(listOf(apiModule, wordDataSourceModule, repositoryModule, viewModelModule))

        }
    }
}