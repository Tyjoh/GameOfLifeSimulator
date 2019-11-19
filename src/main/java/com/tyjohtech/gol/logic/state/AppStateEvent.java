package com.tyjohtech.gol.logic.state;

import com.tyjohtech.gol.util.event.Event;

public class AppStateEvent implements Event {

    private AppState newState;

    public AppStateEvent(AppState newState) {
        this.newState = newState;
    }

    public AppState getNewState() {
        return newState;
    }
}
