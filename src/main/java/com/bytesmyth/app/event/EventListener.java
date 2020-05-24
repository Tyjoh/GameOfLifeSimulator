package com.bytesmyth.app.event;

public interface EventListener<T extends Event> {

    void handle(T event);

}
