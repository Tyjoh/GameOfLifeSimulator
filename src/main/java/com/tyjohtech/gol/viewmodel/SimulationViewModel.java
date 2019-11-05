package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.Simulation;
import com.tyjohtech.gol.model.StandardRule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SimulationViewModel {

    public static final int DEFAULT_SIMULATION_SPEED = 500;
    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private Simulation simulation;

    private double simulationSpeed = 1;
    private int iterationCount = -1;

    public SimulationViewModel(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.SIMULATING) {
            this.simulation = new Simulation(boardViewModel.getBoard(), new StandardRule());
        }
    }

    public void doStep() {
        this.simulation.step();
        this.boardViewModel.setBoard(this.simulation.getBoard());
    }

    public void start() {
        Duration frameDuration = Duration.millis(DEFAULT_SIMULATION_SPEED * (1 / simulationSpeed));
        this.timeline = new Timeline(new KeyFrame(frameDuration, event -> this.doStep()));
        this.timeline.setCycleCount(iterationCount);
        this.timeline.play();
    }

    public void stop() {
        this.timeline.stop();
    }

    public void setSimulationSpeed(double simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }
}
