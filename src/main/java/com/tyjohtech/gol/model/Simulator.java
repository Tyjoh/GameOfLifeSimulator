package com.tyjohtech.gol.model;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.rule.StandardRule;
import com.tyjohtech.gol.util.property.Property;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private static final int DEFAULT_SIMULATION_SPEED = 500;
    private Timeline timeline;
    private Simulation simulation;

    private Property<Board> initialBoard = new Property<>();
    private Property<Board> currentBoard = new Property<>();

    private double simulationSpeed = 1;
    private int iterationCount = -1;

    public Simulator(Property<Board> initialBoard) {
        this.initialBoard.bindTo(initialBoard);
        this.simulation = new Simulation(initialBoard.get(), new StandardRule());
        this.currentBoard.set(this.simulation.getBoard());
    }

    public void doStep() {
        this.simulation.step();
        this.currentBoard.set(this.simulation.getBoard());
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

    public void reset() {
        stop();
        this.simulation = new Simulation(initialBoard.get(), new StandardRule());
        this.currentBoard.set(this.simulation.getBoard());
    }

    public Property<Board> getInitialBoard() {
        return initialBoard;
    }

    public Property<Board> getCurrentBoard() {
        return currentBoard;
    }
}
