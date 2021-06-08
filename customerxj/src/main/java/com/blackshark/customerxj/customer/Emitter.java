package com.blackshark.customerxj.customer;

/**
 * 事件发射器
 * 和被观察者有关系，被观察者发生变化，要通过事件发射器来发送事件。用一个接口来建立关系
 * @param <T>
 */
public interface Emitter<T> {
    void onNext(T t);
    void onComplete();
    void onError(Throwable throwable);
}
