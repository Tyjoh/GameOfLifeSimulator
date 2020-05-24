package com.bytesmyth.app.command;

import com.bytesmyth.app.state.StateRegistry;

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
