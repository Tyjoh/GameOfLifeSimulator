package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.command.Command;
import com.tyjohtech.gol.state.SimulatorState;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);

    @Override
    default Class<SimulatorState> getStateClass() {
        return SimulatorState.class;
    }
}
