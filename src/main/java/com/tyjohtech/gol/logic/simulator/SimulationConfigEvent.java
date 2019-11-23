package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.util.event.Event;

public class SimulationConfigEvent<T> implements Event {

    private String fieldName;
    private T value;

    public SimulationConfigEvent(String fieldName, T value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public T getValue() {
        return value;
    }
}
