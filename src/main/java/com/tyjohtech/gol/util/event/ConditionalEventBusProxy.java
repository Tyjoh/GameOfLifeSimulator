package com.tyjohtech.gol.util.event;

import java.util.HashMap;
import java.util.Map;

public class ConditionalEventBusProxy implements EventBus {

    private EventBus parent;
    private Condition condition;
    private Map<EventListener, EventListener> proxyMap = new HashMap<>();

    public ConditionalEventBusProxy(EventBus parent, Condition condition) {
        this.parent = parent;
        this.condition = condition;
    }

    @Override
    public <T extends Event> void emit(T event) {
        parent.emit(event);
    }

    @Override
    public <T extends Event> void listenFor(Class<T> clazz, EventListener<T> listener) {
        EventListener<T> proxy = event -> {
            if (condition.evaluate()) {
                listener.handleEvent(event);
            }
        };
        proxyMap.put(listener, proxy);
        parent.listenFor(clazz, proxy);
    }

    @Override
    public <T extends Event> void cancel(Class<T> clazz, EventListener<T> listener) {
        EventListener proxy = proxyMap.get(listener);
        parent.cancel(clazz, proxy);
    }
}
