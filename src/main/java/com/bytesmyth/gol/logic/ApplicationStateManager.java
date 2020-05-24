package com.bytesmyth.gol.logic;

import com.bytesmyth.gol.util.Property;

public class ApplicationStateManager {

    private Property<ApplicationState> applicationState = new Property<>(ApplicationState.EDITING);

    public ApplicationStateManager() {
    }

    public Property<ApplicationState> getApplicationState() {
        return applicationState;
    }
}
