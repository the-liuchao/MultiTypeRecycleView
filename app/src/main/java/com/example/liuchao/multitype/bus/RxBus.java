package com.example.liuchao.multitype.bus;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yangrenije on 2017/7/4.
 */

public class RxBus {
    private final Subject mBus;

    private RxBus() {
        mBus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        return Holder.rxBus;
    }

    private static final class Holder {
        private final static RxBus rxBus = new RxBus();
    }

    public <T> Observable register(Class<T> event) {
        return mBus.ofType(event);
    }

    public void unRegister(Subscription subscriber) {
        subscriber.unsubscribe();
    }

}
