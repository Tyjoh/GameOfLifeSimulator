package com.bytesmyth.gol.logic.simulator;

import com.bytesmyth.app.command.Command;
import com.bytesmyth.gol.state.SimulatorState;

public interface SimulatorCommand extends Command<SimulatorState> {
    @Override
    void execute(SimulatorState simulatorState);

    @Override
    default Class<SimulatorState> getStateClass() {
        return SimulatorState.class;
    }
}
