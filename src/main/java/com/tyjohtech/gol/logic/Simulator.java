package com.tyjohtech.gol.logic;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.Simulation;
import com.tyjohtech.gol.model.StandardRule;
import com.tyjohtech.gol.util.Property;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private ApplicationStateManager applicationStateManager;
    private Simulation simulation;

    private Property<Board> initialBoard = new Property<>();
    private Property<Board> currentBoard = new Property<>();

    public Simulator(ApplicationStateManager applicationStateManager) {
        this.applicationStateManager = applicationStateManager;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> this.doStep()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void handle(SimulatorEvent event) {
        switch (event.getEventType()) {
            case START:
                start();
                break;
            case STOP:
                stop();
                break;
            case STEP:
                doStep();
                break;
            case RESET:
                reset();
                break;
        }
    }

    private void doStep() {
        if (applicationStateManager.getApplicationState().get() != ApplicationState.SIMULATING) {
            this.simulation = new Simulation(initialBoard.get(), new StandardRule());
            applicationStateManager.getApplicationState().set(ApplicationState.SIMULATING);
        }

        this.simulation.step();
        this.currentBoard.set(simulation.getBoard());
    }

    private void start() {
        this.timeline.play();
    }

    private void stop() {
        this.timeline.stop();
    }

    private void reset() {
//        this.simulation = new Simulation(initialBoard.get(), new StandardRule());
        this.applicationStateManager.getApplicationState().set(ApplicationState.EDITING);
    }

    public Property<Board> getInitialBoard() {
        return initialBoard;
    }

    public Property<Board> getCurrentBoard() {
        return currentBoard;
    }
}
