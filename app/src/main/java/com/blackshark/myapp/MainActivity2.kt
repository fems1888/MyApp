package com.blackshark.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity2 : AppCompatActivity() {
    lateinit var disposable: Disposable
    lateinit var ss: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        disposable = Observable.create<String> { emitter ->
            println("=======emitter" + "  " + Thread.currentThread().name)
            Thread.sleep(5000)
            emitter!!.onNext("123")
            emitter!!.onComplete()
        }.subscribeOn(Schedulers.io()).map<Any> { s -> s + "jackie" }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { o -> Observable.just(o) }
//            .subscribe(object : Observer<Any?> {
//                override fun onSubscribe(d: @NonNull Disposable?) {
//                    println("=======onSubscribe" + "  " + Thread.currentThread().name)
//                }
//
//                override fun onNext(o: @NonNull Any?) {
//                    println("=======onNext" + "  $o  " + Thread.currentThread().name)
//                }
//
//                override fun onError(e: @NonNull Throwable?) {
//                    println("=======onError" + "  " + Thread.currentThread().name)
//                }
//
//                override fun onComplete() {
//                    println("=======onComplete" + "  " + Thread.currentThread().name)
//                }
//            })

            .subscribe(object : Consumer<Any?> {
                override fun accept(t: Any?) {
                    println("=======accept" + "  $t  " + Thread.currentThread().name)
                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        ss.dispose()
    }
}