package com.tyjohtech.app.property;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {

    private T value;
    private List<ChangeListener<T>> listeners = new LinkedList<>();

    public Property(T value) {
        this.value = value;
    }

    public Property() {
        this(null);
    }

    public void listen(ChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    public void set(T newValue) {
        this.value = newValue;
        notifyListeners();
    }

    public T get() {
        return this.value;
    }

    public boolean isPresent() {
        return value != null;
    }

    private void notifyListeners() {
        for (ChangeListener<T> listener : listeners) {
            listener.onChange(this.value);
        }
    }

}
