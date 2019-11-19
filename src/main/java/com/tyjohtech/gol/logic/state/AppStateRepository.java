package com.tyjohtech.gol.logic.state;

import com.tyjohtech.gol.util.property.Property;

public class AppStateRepository {

    private Property<AppState> applicationState = new Property<>();

    public AppStateRepository(AppState currentState) {
        this.applicationState.set(currentState);
    }

    public void handle(AppStateEvent event) {
        applicationState.set(event.getNewState());
    }

    public Property<AppState> getApplicationState() {
        return applicationState;
    }
}
