package com.blackshark.myapp

import android.app.Application
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler(object : Consumer<Throwable>{
            override fun accept(t: Throwable?) {
                println("============  $t")
            }
        })
    }
}