package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.util.command.CommandProcessor;

public class SimulationConfigEventHandler {

    private SimulationConfig simulationConfig;
    private CommandProcessor commandProcessor;

    public SimulationConfigEventHandler(SimulationConfig simulationConfig, CommandProcessor commandProcessor) {
        this.simulationConfig = simulationConfig;
        this.commandProcessor = commandProcessor;
    }

    public <T> void handle(SimulationConfigEvent<T> configEvent) {
        Object currentValue = simulationConfig.get(configEvent.getFieldName());
        Object newValue = configEvent.getValue();
        if (!currentValue.equals(newValue)) {
            commandProcessor.execute(new SimulationConfigCommand(simulationConfig, configEvent.getFieldName(), configEvent.getValue()));
        }
    }

}
