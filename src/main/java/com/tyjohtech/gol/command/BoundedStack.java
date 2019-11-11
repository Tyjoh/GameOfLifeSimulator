package com.tyjohtech.gol.command;

import java.util.Stack;

public class BoundedStack<T> extends Stack<T> {

    private int maxSize;

    public BoundedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public T push(T item) {
        if (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(item);
    }
}
