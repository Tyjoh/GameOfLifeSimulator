package com.bytesmyth.gol.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventBusTest {

    private EventBus eventBus;

    @BeforeEach
    void setUp() {
        eventBus = new EventBus();
    }

    @Test
    void singleListenerCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class, listener);

        eventBus.emit(event);

        assertTrue(listener.listenerCalled);
    }

    @Test
    void multipleListenersCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener1 = new TestEventListener<>();
        TestEventListener<HelloEvent> listener2 = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class, listener1);
        eventBus.listenFor(HelloEvent.class, listener2);

        eventBus.emit(event);

        assertTrue(listener1.listenerCalled);
        assertTrue(listener2.listenerCalled);
    }

    @Test
    void multipleEventTypes() {
        WorldEvent worldEvent = new WorldEvent();

        TestEventListener<HelloEvent> helloListener = new TestEventListener<>();
        TestEventListener<WorldEvent> worldListener = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class, helloListener);
        eventBus.listenFor(WorldEvent.class, worldListener);

        eventBus.emit(worldEvent);

        assertFalse(helloListener.listenerCalled);
        assertTrue(worldListener.listenerCalled);
    }

    private static class TestEventListener<T extends Event> implements EventListener<T> {

        boolean listenerCalled;

        @Override
        public void handle(T event) {
            listenerCalled = true;
        }
    }

    private static class HelloEvent implements Event {
        private String value = "Hello";
    }

    private static class WorldEvent implements Event {
        private String value = "World";
    }

}