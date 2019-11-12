package com.tyjohtech.gol.util.event;

import java.util.Stack;

public class EventStack<T extends Event> extends Stack<T> {

    private int size;

    public EventStack(int size) {
        this.size = size;
    }

    @Override
    public T push(T item) {
        if (this.size() >= size) {
            this.remove(0);
        }
        return super.push(item);
    }
}
