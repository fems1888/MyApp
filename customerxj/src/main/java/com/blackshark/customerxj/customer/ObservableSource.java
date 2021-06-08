package com.blackshark.customerxj.customer;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
