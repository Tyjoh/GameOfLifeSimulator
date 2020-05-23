package com.tyjohtech.app.property;

import java.util.LinkedList;
import java.util.List;

public class AbstractObservable<T> implements Observable<T> {

    private List<ChangeListener<T>> listeners = new LinkedList<>();

    public void listen(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    protected void notifyListeners(T t) {
        for (ChangeListener<T> listener : listeners) {
            listener.onChange(t);
        }
    }
}
