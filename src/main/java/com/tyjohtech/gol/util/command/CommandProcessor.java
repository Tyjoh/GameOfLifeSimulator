package com.tyjohtech.gol.util.command;

public interface CommandProcessor {
    void execute(Command command);

    void undo();
}
