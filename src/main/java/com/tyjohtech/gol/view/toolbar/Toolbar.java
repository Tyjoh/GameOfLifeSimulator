package com.tyjohtech.gol.view.toolbar;

import com.tyjohtech.gol.model.SimulationEvent;
import com.tyjohtech.gol.util.event.EventBus;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private ToolbarViewModel toolbarViewModel;
    private EventBus eventBus;

    public Toolbar(ToolbarViewModel toolbarViewModel, EventBus eventBus) {
        this.toolbarViewModel = toolbarViewModel;
        this.eventBus = eventBus;
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
        eventBus.emit(SimulationEvent.stop());
    }

    private void handleStart(ActionEvent actionEvent) {
        eventBus.emit(SimulationEvent.start());

    }

    private void handleReset(ActionEvent actionEvent) {
        eventBus.emit(SimulationEvent.reset());
    }

    private void handleStep(ActionEvent actionEvent) {
        eventBus.emit(SimulationEvent.step());
    }

    private void handleErase(ActionEvent actionEvent) {
        System.out.println("Erase pressed");
//        this.editorViewModel.setDrawMode(CellState.DEAD);
    }

    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("Draw pressed");
//        this.editorViewModel.setDrawMode(CellState.ALIVE);
    }

}
