package com.tyjohtech.gol.view.settings;

import com.tyjohtech.gol.logic.simulator.config.SimulationConfigEvent;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.KeyValuePane;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SettingsPane extends KeyValuePane {

    private EventBus eventBus;

    private final Slider speed;
    private final CheckBox limitIterations;
    private final TextField iterations;

    public SettingsPane(SettingsViewModel settingsViewModel, EventBus eventBus) {
        this.eventBus = eventBus;

        speed = new Slider(0.1, 10, 1);
        speed.valueChangingProperty().addListener(this::onSpeedChange);
        settingsViewModel.getSimulationSpeed().listen(speed::setValue);
        this.add("Sim Speed", speed);

        limitIterations = new CheckBox();
        limitIterations.selectedProperty().addListener(this::onLimitIterationChange);
        settingsViewModel.getLimitIterations().listen(limitIterations::setSelected);
        this.add("Limit Iterations", limitIterations);

        iterations = new TextField("25");
        iterations.addEventHandler(KeyEvent.KEY_RELEASED, this::submitIterations);
        iterations.focusedProperty().addListener(this::onIterationsFocus);
        settingsViewModel.getIterationCount().listen(s -> iterations.setText(s.toString()));
        this.add("Iteration Count", iterations);
    }

    private void onSpeedChange(Observable o) {
        if (!speed.isValueChanging()) {
            eventBus.emit(new SimulationConfigEvent<>("simulationSpeed", speed.getValue()));
        }
    }

    private void onLimitIterationChange(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        eventBus.emit(new SimulationConfigEvent<>("limitIterations", limitIterations.isSelected()));
    }

    private void submitIterations(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.requestFocus();
            emitIterationCountEvent();
        }
    }

    private void onIterationsFocus(Observable observable) {
        if (!iterations.isFocused()) {
            emitIterationCountEvent();
        }
    }

    private void emitIterationCountEvent() {
        String text = iterations.getText();
        try {
            int iterationCount = Integer.parseInt(text);
            eventBus.emit(new SimulationConfigEvent<>("iterationCount", iterationCount));
        } catch (NumberFormatException ignored) {
        }
    }
}
