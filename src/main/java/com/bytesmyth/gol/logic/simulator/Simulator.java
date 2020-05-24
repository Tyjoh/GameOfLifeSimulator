package com.bytesmyth.gol.logic.simulator;

import com.bytesmyth.app.command.CommandExecutor;
import com.bytesmyth.gol.logic.ApplicationState;
import com.bytesmyth.gol.logic.ApplicationStateManager;
import com.bytesmyth.gol.model.Simulation;
import com.bytesmyth.gol.model.StandardRule;
import com.bytesmyth.gol.state.SimulatorState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private ApplicationStateManager applicationStateManager;
    private Simulation simulation;

    private SimulatorState state;
    private CommandExecutor commandExecutor;

    private boolean reset = true;

    public Simulator(ApplicationStateManager applicationStateManager, SimulatorState state, CommandExecutor commandExecutor) {
        this.applicationStateManager = applicationStateManager;
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
            applicationStateManager.getApplicationState().set(ApplicationState.SIMULATING);
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
        this.applicationStateManager.getApplicationState().set(ApplicationState.EDITING);
    }
}
