package com.blackshark.customerxj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blackshark.customerxj.customer.Emitter;
import com.blackshark.customerxj.customer.Observable;
import com.blackshark.customerxj.customer.ObservableOnSubscribe;
import com.blackshark.customerxj.customer.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onNext("333");
                emitter.onError(new Throwable());
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                System.out.println("=====onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("=====onNext" + s);
            }

            @Override
            public void onComplete() {
                System.out.println("=====onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("=====onError");
            }
        });
    }
}