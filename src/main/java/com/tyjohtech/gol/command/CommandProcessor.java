package com.tyjohtech.gol.command;

public interface CommandProcessor {
    void execute(Command command);

    void undo();
}
