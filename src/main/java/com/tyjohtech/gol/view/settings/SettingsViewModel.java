package com.tyjohtech.gol.view.settings;

import com.tyjohtech.gol.util.property.Property;

public class SettingsViewModel {

    private Property<Boolean> limitIterations = new Property<>();
    private Property<Double> simulationSpeed = new Property<>();
    private Property<Integer> iterationCount = new Property<>();

    public Property<Boolean> getLimitIterations() {
        return limitIterations;
    }

    public Property<Double> getSimulationSpeed() {
        return simulationSpeed;
    }

    public Property<Integer> getIterationCount() {
        return iterationCount;
    }
}
