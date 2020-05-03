package com.tyjohtech.app.command;

public interface Command<T> {

    void execute(T t);

    Class<T> getStateClass();

}
