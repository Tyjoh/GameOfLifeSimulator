package com.tyjohtech.gol.components.simulator;

import com.tyjohtech.app.command.CommandExecutor;
import com.tyjohtech.gol.components.board.ApplicationState;
import com.tyjohtech.gol.components.board.BoardState;
import com.tyjohtech.gol.model.Simulation;
import com.tyjohtech.gol.model.StandardRule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private BoardState boardState;
    private Simulation simulation;

    private SimulatorState state;
    private CommandExecutor commandExecutor;

    private boolean reset = true;

    public Simulator(BoardState boardState, SimulatorState state, CommandExecutor commandExecutor) {
        this.boardState = boardState;
        this.state = state;
        this.commandExecutor = commandExecutor;

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
        if (reset) {
            reset = false;
            this.simulation = new Simulation(state.getBoard().get(), new StandardRule());
            boardState.getApplicationState().set(ApplicationState.SIMULATING);
        }

        this.simulation.step();

        SimulatorCommand command = (state) -> state.getBoard().set(simulation.getBoard());
        commandExecutor.execute(command);
    }

    private void start() {
        this.timeline.play();
    }

    private void stop() {
        this.timeline.stop();
    }

    private void reset() {
        reset = true;
        this.boardState.getApplicationState().set(ApplicationState.EDITING);
    }
}
