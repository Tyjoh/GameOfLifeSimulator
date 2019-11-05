package com.tyjohtech.gol.view;

import com.tyjohtech.gol.viewmodel.SimulationViewModel;
import javafx.beans.Observable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SettingsPane extends AnchorPane {

    private final TextField iterationCount;
    private SimulationViewModel simulationViewModel;
    private final Slider simulationSpeed;
    private final CheckBox limitIterations;

    public SettingsPane(SimulationViewModel simulationViewModel) {
        this.getStyleClass().addAll("pane");

        KeyValuePane keyValuePane = new KeyValuePane();

        this.simulationViewModel = simulationViewModel;
        simulationSpeed = new Slider(1, 10, 1);
        simulationSpeed.valueProperty().addListener(this::onSpeedChanged);
        keyValuePane.add("Simulation Speed", simulationSpeed);

        limitIterations = new CheckBox();
        limitIterations.selectedProperty().addListener(this::onLimitIterationsChange);
        keyValuePane.add("Limit Iterations", limitIterations);

        iterationCount = new TextField("4352");
        iterationCount.textProperty().addListener(this::onIterationCountChange);
        keyValuePane.add("Iteration Count", iterationCount);

        AnchorPane.setLeftAnchor(keyValuePane, 0.0);
        AnchorPane.setRightAnchor(keyValuePane, 0.0);
        AnchorPane.setTopAnchor(keyValuePane, 0.0);
        AnchorPane.setBottomAnchor(keyValuePane, 0.0);

        this.getChildren().add(keyValuePane);
    }

    private void onIterationCountChange(Observable observable) {
        updateIterationCount();
    }

    private void updateIterationCount() {
        String iterationCount = this.iterationCount.getText();
        int count = Integer.parseInt(iterationCount);
        simulationViewModel.setIterationCount(count);
    }

    private void onLimitIterationsChange(Observable observable) {
        if (this.limitIterations.isSelected()) {
            //set to text field value
            updateIterationCount();
        } else {
            this.simulationViewModel.setIterationCount(-1);
        }
    }

    private void onSpeedChanged(Observable observable) {
        simulationViewModel.setSimulationSpeed(simulationSpeed.getValue());
    }

}
