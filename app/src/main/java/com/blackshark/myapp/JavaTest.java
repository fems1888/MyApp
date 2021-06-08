package com.blackshark.myapp;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class JavaTest {
    public static void main(String[] args) {
        System.out.println(111);
    }

    void test(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("123");
                emitter.onComplete();
            }
        }).map(new Function<String, Object>() {
            @Override
            public Object apply(String s) throws Throwable {
                return s+"jackie";
            }
        })
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {
                        return Observable.just(o);
                    }
                })
                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("=======onSubscribe" +"  "+Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("=======onNext" +"  "+Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("=======onError" +"  "+Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                System.out.println("=======onComplete" +"  "+Thread.currentThread().getName());
            }
        })
//        .subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Throwable {
//
//            }
//        })
        ;
    }
}
