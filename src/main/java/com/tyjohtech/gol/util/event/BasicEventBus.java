package com.tyjohtech.gol.util.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BasicEventBus implements EventBus {

    private Map<Class<? extends Event>, ListenerCollection> eventListeners = new HashMap<>();

    @Override
    public <T extends Event> void emit(T event) {
        ListenerCollection<T> listenerCollection = getListenerCollection(event.getClass());
        for (EventListener<T> tEventListener : listenerCollection) {
            tEventListener.handleEvent(event);
        }
    }

    @Override
    public <T extends Event> void listenFor(Class<T> clazz, EventListener<T> listener) {
        ListenerCollection<T> listenerCollection = getListenerCollection(clazz);
        listenerCollection.add(listener);
    }

    @Override
    public <T extends Event> void cancel(Class<T> clazz, EventListener<T> listener) {
        getListenerCollection(clazz).remove(listener);
    }

    private <T extends Event> ListenerCollection<T> getListenerCollection(Class<? extends Event> clazz) {
        if (!eventListeners.containsKey(clazz)) {
            eventListeners.put(clazz, new ListenerCollection<T>());
        }
        return eventListeners.get(clazz);
    }

    private static class ListenerCollection<T extends Event> extends LinkedList<EventListener<T>> {
    }

}
