package com.tyjohtech.gol.util.event;

public interface EventSource {

    <T extends Event> void listenFor(Class<T> clazz, EventListener<T> listener);

    <T extends Event> void cancel(Class<T> clazz, EventListener<T> listener);

}
