package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.util.command.Command;

public class SimulationConfigCommand implements Command {

    private SimulationConfig config;
    private final String propertyKey;
    private final Object value;
    private Object prevValue;

    public SimulationConfigCommand(SimulationConfig config, String propertyKey, Object value) {
        this.config = config;
        this.propertyKey = propertyKey;
        this.value = value;
    }

    @Override
    public void execute() {
        prevValue = config.get(propertyKey);
        config.set(propertyKey, value);
    }

    @Override
    public void undo() {
        System.out.printf("Undoing Config command. OldValue = %s, NewValue = %s\n", prevValue, value);
        config.set(propertyKey, prevValue);
    }
}
