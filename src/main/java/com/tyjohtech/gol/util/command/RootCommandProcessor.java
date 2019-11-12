package com.tyjohtech.gol.util.command;

public class RootCommandProcessor implements CommandProcessor {

    private BoundedStack<Command> commandStack;

    public RootCommandProcessor() {
        this.commandStack = new BoundedStack<>(256);
    }

    @Override
    public void execute(Command command) {
        command.execute();
        commandStack.push(command);
    }

    @Override
    public void undo() {
        if (commandStack.size() > 0) {
            Command command = commandStack.pop();
            command.undo();
        }
    }
}
