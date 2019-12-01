package com.tyjohtech.gol.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void constructWithInitialValue() {
        String expected = "Hello World";
        Property<String> stringProperty = new Property<>(expected);

        assertEquals(expected, stringProperty.get());
    }

    @Test
    void constructWithNoInitialValue() {
        Property<String> stringProperty = new Property<>();

        assertNull(stringProperty.get());
    }

    @Test
    void notifiesListener() {
        double expected = 2.0;
        DoubleListener listener = new DoubleListener();
        Property<Double> doubleProperty = new Property<>(1.0);
        doubleProperty.listen(listener);

        doubleProperty.set(expected);

        assertTrue(listener.notified);
        assertEquals(expected, listener.value);
    }

    private class DoubleListener implements SimpleChangeListener<Double> {

        private boolean notified = false;
        private double value;

        @Override
        public void valueChanged(Double value) {
            notified = true;
            this.value = value;
        }
    }
}