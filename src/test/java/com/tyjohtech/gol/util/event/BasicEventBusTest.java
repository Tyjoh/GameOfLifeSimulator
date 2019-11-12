package com.tyjohtech.gol.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicEventBusTest {

    private BasicEventBus eventBus;

    @BeforeEach
    void setUp() {
        eventBus = new BasicEventBus();
    }

    @Test
    void emit_oneListener() {
        Listener listener = new Listener();
        eventBus.listenFor(FooEvent.class, listener);

        eventBus.emit(new FooEvent());

        assertTrue(listener.notified);
    }

    @Test
    void emit_twoListeners() {
        Listener listener1 = new Listener();
        Listener listener2 = new Listener();
        eventBus.listenFor(FooEvent.class, listener1);
        eventBus.listenFor(FooEvent.class, listener2);

        eventBus.emit(new FooEvent());

        assertTrue(listener1.notified);
        assertTrue(listener2.notified);
    }

    @Test
    void emit_listenersForMultipleTypes() {
        Listener fooListener = new Listener();
        Listener barListener = new Listener();

        eventBus.listenFor(FooEvent.class, fooListener);
        eventBus.listenFor(BarEvent.class, barListener);

        eventBus.emit(new BarEvent());

        assertFalse(fooListener.notified);
        assertTrue(barListener.notified);
        assertEquals(BarEvent.class, barListener.aClass);
    }

    @Test
    void emit_typedListener() {
        FooListener fooListener = new FooListener();
        eventBus.listenFor(FooEvent.class, fooListener);

        eventBus.emit(new FooEvent());

        assertNotNull(fooListener.event);
    }

    private static class FooListener implements EventListener<FooEvent> {
        FooEvent event;

        @Override
        public void handleEvent(FooEvent event) {
            this.event = event;
        }
    }

    private static class Listener implements EventListener {

        private boolean notified;
        private Class aClass;

        @Override
        public void handleEvent(Event event) {
            notified = true;
            aClass = event.getClass();
        }
    }

    private static class FooEvent implements Event {
        private String fooData = "FOO";
    }

    private static class BarEvent implements Event {
        private String barData = "BAR";
    }

}