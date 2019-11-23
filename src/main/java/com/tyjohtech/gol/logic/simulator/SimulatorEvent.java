package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.util.event.Event;

public class SimulatorEvent implements Event {

    public enum Type {
        START,
        STOP,
        STEP,
        RESET
    }

    private Type type;

    private SimulatorEvent(Type type) {
        this.type = type;
    }

    public static SimulatorEvent start() {
        return new SimulatorEvent(Type.START);
    }

    public static SimulatorEvent stop() {
        return new SimulatorEvent(Type.STOP);
    }

    public static SimulatorEvent step() {
        return new SimulatorEvent(Type.STEP);
    }

    public static SimulatorEvent reset() {
        return new SimulatorEvent(Type.RESET);
    }

    public Type getType() {
        return type;
    }
}
