package com.example.cryptoinfo

import android.app.Application
import com.example.cryptoinfo.di.CryptoInfoComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = CryptoInfoComponent()
    }

    override fun onTerminate() {
        super.onTerminate()
        component = null
    }

    companion object {
        private var component: CryptoInfoComponent? = null
        fun requireComponent(): CryptoInfoComponent {
            return component ?: throw IllegalStateException("Dependencies component not exist")
        }
    }
}