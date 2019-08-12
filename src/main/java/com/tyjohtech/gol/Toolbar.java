package com.tyjohtech.gol;

import com.tyjohtech.gol.model.CellState;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    private Simulator simulator;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;
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
        this.simulator.stop();
    }

    private void handleStart(ActionEvent actionEvent) {
        switchToSimulatingState();
        this.simulator.start();
    }

    private void handleReset(ActionEvent actionEvent) {
        this.mainView.setApplicationState(MainView.EDITING);
        this.simulator = null;
        this.mainView.draw();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("Step pressed");

        switchToSimulatingState();

        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

    private void switchToSimulatingState() {
        if (this.mainView.getApplicationState() == MainView.EDITING) {
            this.mainView.setApplicationState(MainView.SIMULATING);
            this.simulator = new Simulator(this.mainView, this.mainView.getSimulation());
        }
    }

    private void handleErase(ActionEvent actionEvent) {
        System.out.println("Erase pressed");
        this.mainView.setDrawMode(CellState.DEAD);
    }

    private void handleDraw(ActionEvent actionEvent) {
        System.out.println("Draw pressed");
        this.mainView.setDrawMode(CellState.ALIVE);
    }

}
