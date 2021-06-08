package com.blackshark.customerxj.customer;

public interface Observer<T> {
    void onSubscribe();//onXXX是名词 一个事件 一般是回调使用的方法  而xxxOn是动作  发生事件 执行操作的代码块。发生订阅关系的回调
    void onNext(T t);
    void onComplete();
    void onError(Throwable throwable);
}
