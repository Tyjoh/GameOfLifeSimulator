package com.tyjohtech.app.property;

public class Property<T> extends AbstractObservable<T> {

    private T value;

    public Property(T value) {
        this.value = value;
    }

    public Property() {
        this(null);
    }

    public void set(T newValue) {
        this.value = newValue;
        notifyListeners(value);
    }

    public T get() {
        return this.value;
    }

    public boolean isPresent() {
        return value != null;
    }

}
