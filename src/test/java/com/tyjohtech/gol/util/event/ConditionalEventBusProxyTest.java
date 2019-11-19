package com.tyjohtech.gol.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionalEventBusProxyTest {

    private EventBus parent;
    private EventBus proxy;
    private TestCondition condition;

    @BeforeEach
    void setUp() {
        parent = new BasicEventBus();
        condition = new TestCondition();
        proxy = new ConditionalEventBusProxy(parent, condition);
    }

    @Test
    void listen_conditionFalse() {
        TestListener<TestEvent1> testListener = new TestListener<>();
        proxy.listenFor(TestEvent1.class, testListener);
        condition.value = false;

        parent.emit(new TestEvent1());

        assertFalse(testListener.notified);
    }

    @Test
    void listen_conditionTrue() {
        TestListener<TestEvent1> testListener = new TestListener<>();
        proxy.listenFor(TestEvent1.class, testListener);
        condition.value = true;

        parent.emit(new TestEvent1());

        assertTrue(testListener.notified);
    }

    @Test
    void listen_conditionFalseMultipleListeners() {
        TestListener<TestEvent1> testListener1 = new TestListener<>();
        proxy.listenFor(TestEvent1.class, testListener1);
        TestListener<TestEvent2> testListener2 = new TestListener<>();
        proxy.listenFor(TestEvent2.class, testListener2);
        condition.value = false;

        parent.emit(new TestEvent1());
        parent.emit(new TestEvent2());

        assertFalse(testListener1.notified);
        assertFalse(testListener2.notified);
    }

    @Test
    void listen_conditionTrueMultipleListeners() {
        TestListener<TestEvent1> testListener1 = new TestListener<>();
        proxy.listenFor(TestEvent1.class, testListener1);
        TestListener<TestEvent2> testListener2 = new TestListener<>();
        proxy.listenFor(TestEvent2.class, testListener2);
        condition.value = true;

        parent.emit(new TestEvent1());
        parent.emit(new TestEvent2());

        assertTrue(testListener1.notified);
        assertTrue(testListener2.notified);
    }

    private static class TestEvent1 implements Event {
    }

    private static class TestEvent2 implements Event {
    }

    private static class TestCondition implements Condition {

        private boolean value;

        @Override
        public boolean evaluate() {
            return value;
        }
    }

    private static class TestListener<T extends Event> implements EventListener<T> {

        boolean notified = false;

        @Override
        public void handleEvent(T event) {
            notified = true;
        }
    }
}