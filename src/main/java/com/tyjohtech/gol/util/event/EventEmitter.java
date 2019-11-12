package com.tyjohtech.gol.util.event;

public interface EventEmitter {

    <T extends Event> void emit(T event);

}
