package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.model.Simulation;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.rule.StandardRule;
import com.tyjohtech.gol.util.property.Property;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private static final int DEFAULT_SIMULATION_SPEED = 500;
    private Timeline timeline;
    private Simulation simulation;

    private Property<Board> initialBoard = new Property<>();
    private Property<Board> currentBoard = new Property<>();

    private SimulationConfig simulationConfig;

    public Simulator(Property<Board> initialBoard, SimulationConfig simulationConfig) {
        this.initialBoard.bindTo(initialBoard);
        this.simulationConfig = simulationConfig;

        this.simulation = new Simulation(initialBoard.get(), new StandardRule());
        this.currentBoard.set(this.simulation.getBoard());

        this.timeline = new Timeline();
        simulationConfig.getIterationCount().listen(value -> updateTimeline());
        simulationConfig.getLimitIterations().listen(value -> updateTimeline());
        simulationConfig.getSimulationSpeed().listen(value -> updateTimeline());
    }

    public void doStep() {
        this.simulation.step();
        this.currentBoard.set(this.simulation.getBoard());
    }

    public void start() {
        updateTimeline();
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

    private void updateTimeline() {
        boolean restart = timeline.getStatus() == Animation.Status.RUNNING;

        timeline.stop();

        double simulationSpeed = simulationConfig.getSimulationSpeed().get();
        Duration frameDuration = Duration.millis(DEFAULT_SIMULATION_SPEED * (1 / simulationSpeed));
        this.timeline.getKeyFrames().clear();
        this.timeline.getKeyFrames().add(new KeyFrame(frameDuration, event -> this.doStep()));

        if (simulationConfig.getLimitIterations().get()) {
            this.timeline.setCycleCount(simulationConfig.getIterationCount().get());
        } else {
            this.timeline.setCycleCount(Animation.INDEFINITE);
        }

        if (restart) {
            timeline.play();
        }
    }
}
