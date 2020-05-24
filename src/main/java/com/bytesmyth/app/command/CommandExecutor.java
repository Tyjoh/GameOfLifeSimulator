package com.bytesmyth.app.command;

import com.bytesmyth.app.state.StateRegistry;

import java.util.Stack;

public class CommandExecutor {

    private StateRegistry stateRegistry;

    private Stack<UndoableCommand> undoStack = new Stack<>();

    public CommandExecutor(StateRegistry stateRegistry) {
        this.stateRegistry = stateRegistry;
    }

    public <T> void execute(Command<T> command) {
        T state = stateRegistry.getState(command.getStateClass());
        command.execute(state);
    }

    public <T> void execute(UndoableCommand<T> command) {
        T state = stateRegistry.getState(command.getStateClass());
        command.execute(state);
        undoStack.push(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            UndoableCommand command = undoStack.pop();
            Object state = stateRegistry.getState(command.getStateClass());
            command.undo(state);
        }
    }
}
