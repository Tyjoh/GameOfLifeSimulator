package com.tyjohtech.gol.logic.state;

import com.tyjohtech.gol.util.event.Condition;

import java.util.Set;

public class AppStateCondition implements Condition {

    private AppStateRepository appStateRepository;
    private Set<AppState> allowedStates;

    public AppStateCondition(AppStateRepository appStateRepository, Set<AppState> allowedStates) {
        this.appStateRepository = appStateRepository;
        this.allowedStates = allowedStates;
    }

    @Override
    public boolean evaluate() {
        return allowedStates.contains(appStateRepository.getApplicationState().get());
    }
}
