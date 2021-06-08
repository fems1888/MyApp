package com.blackshark.customerxj.customer;

/**
 * 解耦被观察者和事件
 * 事件发送时通过发射器
 */
public interface ObservableOnSubscribe<T> {
    void subscribe(Emitter<T> emitter);
}
