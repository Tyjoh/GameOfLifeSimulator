package com.tyjohtech.app.property;

public interface Observable<T> {
    void listen(ChangeListener<T> listener);
}
