package com.tyjohtech.gol.command;

import com.tyjohtech.gol.state.StateRegistry;

public class CommandExecutor {

    private StateRegistry stateRegistry;

    public CommandExecutor(StateRegistry stateRegistry) {
        this.stateRegistry = stateRegistry;
    }

    public <T> void execute(Command<T> command) {
        T state = stateRegistry.getState(command.getStateClass());
        command.execute(state);
    }
}
