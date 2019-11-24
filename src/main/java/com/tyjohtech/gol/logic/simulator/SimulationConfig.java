package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.util.property.Property;

import java.util.HashMap;
import java.util.Map;

public class SimulationConfig {

    private Property<Double> simulationSpeed = new Property<>();
    private Property<Boolean> limitIterations = new Property<>();
    private Property<Integer> iterationCount = new Property<>();

    private Map<String, Property> propertyMap = new HashMap<>();

    public SimulationConfig() {
        simulationSpeed.set(1.0);
        limitIterations.set(false);
        iterationCount.set(-1);

        propertyMap.put("simulationSpeed", simulationSpeed);
        propertyMap.put("limitIterations", limitIterations);
        propertyMap.put("iterationCount", iterationCount);
    }

    public void set(String propertyKey, Object value) {
        propertyMap.get(propertyKey).set(value);
    }

    public Object get(String propertyKey) {
        return propertyMap.get(propertyKey).get();
    }

    public <T> Property<T> getProperty(String propertyKey) {
        return propertyMap.get(propertyKey);
    }

    public Property<Double> getSimulationSpeed() {
        return simulationSpeed;
    }

    public Property<Boolean> getLimitIterations() {
        return limitIterations;
    }

    public Property<Integer> getIterationCount() {
        return iterationCount;
    }
}
