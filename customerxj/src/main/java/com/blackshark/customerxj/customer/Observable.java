package com.blackshark.customerxj.customer;

public abstract class Observable<T> implements ObservableSource<T>{

    @Override
    public void subscribe(Observer observer) {
        //和谁建立订阅  怎么建立订阅  为了保证拓展 这里只提供一个抽象方法，交给具体的开发人员实现
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<>(source);
    }
}
