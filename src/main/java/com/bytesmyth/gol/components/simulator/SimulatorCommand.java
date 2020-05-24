package com.bytesmyth.gol.components.simulator;

import com.bytesmyth.app.command.Command;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);

    @Override
    default Class<SimulatorState> getStateClass() {
        return SimulatorState.class;
    }
}
