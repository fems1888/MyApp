package com.blackshark.myapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        test()
//        test2()

        startActivity(Intent(this,MainActivity2::class.java))
    }

    private fun test2() {
        Observable.create<String> { emitter ->
            println("=======emitter" + "  " + Thread.currentThread().name)
            emitter!!.onNext("123")
            emitter!!.onComplete()
        }.subscribeOn(Schedulers.io()).map<Any> { s -> s + "jackie" }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { o -> Observable.just(o) }
            .subscribe(object : Observer<Any?> {
                override fun onSubscribe(d: @NonNull Disposable?) {
                    println("=======onSubscribe" + "  " + Thread.currentThread().name)
                }

                override fun onNext(o: @NonNull Any?) {
                    println("=======onNext" + "  $o  " + Thread.currentThread().name)
                }

                override fun onError(e: @NonNull Throwable?) {
                    println("=======onError" + "  " + Thread.currentThread().name)
                }

                override fun onComplete() {
                    println("=======onComplete" + "  " + Thread.currentThread().name)
                }
            })
        //            .subscribe(object : Consumer<Any?> {
//                override fun accept(t: Any?) {
//                    println("=======accept" + "  $t  " + Thread.currentThread().name)
//                }
//
//            })

    }

    private fun test() {
        Observable.create<String> { emitter ->
            emitter!!.onNext("123")
            emitter!!.onComplete()
        }.map<Any> { s -> s + "jackie" }
            .flatMap { o -> Observable.just(o) }
            .subscribe(object : Observer<Any?> {
                override fun onSubscribe(d: @NonNull Disposable?) {
                    println("=======onSubscribe" + "  " + Thread.currentThread().name)
                }

                override fun onNext(o: @NonNull Any?) {
                    println("=======onNext" + "  " + Thread.currentThread().name)
                }

                override fun onError(e: @NonNull Throwable?) {
                    println("=======onError" + "  " + Thread.currentThread().name)
                }

                override fun onComplete() {
                    println("=======onComplete" + "  " + Thread.currentThread().name)
                }
            })
    }
}