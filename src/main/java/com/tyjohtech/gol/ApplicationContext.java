package com.tyjohtech.gol;

import com.tyjohtech.app.command.CommandExecutor;
import com.tyjohtech.app.event.EventBus;
import com.tyjohtech.app.state.StateRegistry;
import com.tyjohtech.app.view.View;

public class ApplicationContext {

    private StateRegistry stateRegistry;
    private EventBus eventBus;
    private CommandExecutor commandExecutor;

    private View view;
    private int boardWidth;
    private int boardHeight;

    public ApplicationContext(StateRegistry stateRegistry, EventBus eventBus, CommandExecutor commandExecutor, View view, int boardWidth, int boardHeight) {
        this.stateRegistry = stateRegistry;
        this.eventBus = eventBus;
        this.commandExecutor = commandExecutor;
        this.view = view;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public StateRegistry getStateRegistry() {
        return stateRegistry;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public View getMainView() {
        return view;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
