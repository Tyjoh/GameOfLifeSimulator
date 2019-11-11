package com.tyjohtech.gol.property;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {

    private T value;
    private List<SimpleChangeListener<T>> listeners = new LinkedList<>();

    public void listen(SimpleChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    public void cancel(SimpleChangeListener<T> listener) {
        this.listeners.remove(listener);
    }

    public void set(T newValue) {
        this.value = newValue;
        notifyListeners();
    }

    public T get() {
        return value;
    }

    public void bindTo(Property<T> property) {
        this.value = property.get();
        property.listen(this::set);
    }

    public boolean isPresent() {
        return value != null;
    }

    private void notifyListeners() {
        for (SimpleChangeListener<T> listener : listeners) {
            listener.valueChanged(value);
        }
    }

}
