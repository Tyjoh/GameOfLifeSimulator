package com.tyjohtech.gol;

import com.tyjohtech.gol.viewmodel.editor.DrawMode;
import com.tyjohtech.gol.viewmodel.editor.EditorViewModel;
import com.tyjohtech.gol.viewmodel.simulation.SimulationViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private EditorViewModel editorViewModel;
    private SimulationViewModel simulationViewModel;

    public Toolbar(EditorViewModel editorViewModel, SimulationViewModel simulationViewModel) {
        this.editorViewModel = editorViewModel;
        this.simulationViewModel = simulationViewModel;
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button stop = new Button("Stop");
        stop.setOnAction(this::handleStop);

        this.getItems().addAll(draw, erase, reset, step, start, stop);
    }

    private void handleStop(ActionEvent actionEvent) {
        this.simulationViewModel.stopSimulation();
    }

    private void handleStart(ActionEvent actionEvent) {
        this.simulationViewModel.startSimulation();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.simulationViewModel.reset();
    }

    private void handleStep(ActionEvent actionEvent) {
        this.simulationViewModel.stepSimulation();
    }

    private void handleErase(ActionEvent actionEvent) {
        this.editorViewModel.setDrawMode(DrawMode.ERASER);
    }

    private void handleDraw(ActionEvent actionEvent) {
        this.editorViewModel.setDrawMode(DrawMode.PENCIL);
    }

}
