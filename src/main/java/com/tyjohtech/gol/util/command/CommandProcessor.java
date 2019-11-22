package com.tyjohtech.gol.util.command;

public interface CommandProcessor {
    /**
     * Calls execute on the command, pushes to the stack.
     *
     * @param command command to execute
     */
    void execute(Command command);

    /**
     * Pushes a command onto the command stack without calling execute.
     *
     * @param command command to add
     */
    void push(Command command);

    /**
     * Pops a command from the stack and calls undo.
     */
    void undo();
}
