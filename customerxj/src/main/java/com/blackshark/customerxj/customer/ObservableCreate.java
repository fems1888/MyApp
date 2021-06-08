package com.blackshark.customerxj.customer;

public class ObservableCreate<T> extends Observable<T>{
    ObservableOnSubscribe source;

    public ObservableCreate(ObservableOnSubscribe source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();//onSubscribe在建立订阅的时候触发
        CreateEmitter<T> emitter = new CreateEmitter<T>(observer);
        source.subscribe(emitter);

    }
    static class CreateEmitter<T> implements Emitter<T>{
        Observer<T> observer;
        boolean done;//onComplete和onError互斥的标识
        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if (done) return;
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if (done) return;
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if (done) return;
            observer.onError(throwable);
            done = true;
        }
    }
}
