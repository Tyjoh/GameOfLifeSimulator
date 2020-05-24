package com.bytesmyth.gol.command;

public interface Command<T> {

    void execute(T t);

    Class<T> getStateClass();

}
