package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.util.Property;

public class ApplicationViewModel {

    private Property<ApplicationState> applicationState = new Property<>(ApplicationState.EDITING);

    public ApplicationViewModel() {
    }

    public Property<ApplicationState> getApplicationState() {
        return applicationState;
    }
}
