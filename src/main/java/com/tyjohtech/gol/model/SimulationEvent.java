package com.tyjohtech.gol.model;

import com.tyjohtech.gol.util.event.Event;

public class SimulationEvent implements Event {

    public enum Type {
        START,
        STOP,
        STEP,
        RESET
    }

    private Type type;

    private SimulationEvent(Type type) {
        this.type = type;
    }

    public static SimulationEvent start() {
        return new SimulationEvent(Type.START);
    }

    public static SimulationEvent stop() {
        return new SimulationEvent(Type.STOP);
    }

    public static SimulationEvent step() {
        return new SimulationEvent(Type.STEP);
    }

    public static SimulationEvent reset() {
        return new SimulationEvent(Type.RESET);
    }

    public Type getType() {
        return type;
    }
}
