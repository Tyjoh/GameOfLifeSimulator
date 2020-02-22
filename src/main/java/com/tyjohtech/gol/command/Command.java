package com.tyjohtech.gol.command;

public interface Command<T> {

    void execute(T t);

}
