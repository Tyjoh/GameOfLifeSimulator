package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.util.event.Event;

public class SimulatorEvent implements Event {

    public enum Type {
        START,
        STOP,
        STEP,
        RESET;
    }

    private Type eventType;

    public SimulatorEvent(Type eventType) {
        this.eventType = eventType;
    }

    public Type getEventType() {
        return eventType;
    }
}