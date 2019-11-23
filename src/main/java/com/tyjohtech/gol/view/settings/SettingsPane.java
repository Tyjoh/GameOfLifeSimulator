package com.tyjohtech.gol.view.settings;

import com.tyjohtech.gol.logic.simulator.SimulationConfigEvent;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.KeyValuePane;
import javafx.beans.Observable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SettingsPane extends KeyValuePane {

    private EventBus eventBus;

    private final Slider speed;
    private final CheckBox limitIterations;
    private final TextField iterations;

    public SettingsPane(EventBus eventBus) {
        this.eventBus = eventBus;

        speed = new Slider(0.1, 10, 1);
        speed.valueProperty().addListener(this::onSpeedChange);
        this.add("Sim Speed", speed);
        limitIterations = new CheckBox();
        limitIterations.selectedProperty().addListener(this::onLimitIterationChange);
        this.add("Setting 2", limitIterations);
        iterations = new TextField("25");
        iterations.textProperty().addListener(this::onIterationChange);
        this.add("Setting 3", iterations);
    }

    private void onSpeedChange(Observable observable) {
        eventBus.emit(new SimulationConfigEvent<>("simulationSpeed", speed.getValue()));
    }

    private void onLimitIterationChange(Observable observable) {
        eventBus.emit(new SimulationConfigEvent<>("limitIterations", limitIterations.isSelected()));
    }

    private void onIterationChange(Observable observable) {
        String text = iterations.getText();
        try {
            int iterationCount = Integer.parseInt(text);
            eventBus.emit(new SimulationConfigEvent<>("iterationCount", iterationCount));
        } catch (NumberFormatException e) {

        }
    }
}
