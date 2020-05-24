package com.bytesmyth.gol.components.simulator;

import com.bytesmyth.app.command.CommandExecutor;
import com.bytesmyth.gol.model.Simulation;
import com.bytesmyth.gol.model.StandardRule;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private Simulation simulation;

    private SimulatorState state;
    private CommandExecutor commandExecutor;

    private boolean reset = true;

    public Simulator(SimulatorState state, CommandExecutor commandExecutor) {
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
            this.state.getSimulating().set(true);
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
        this.state.getSimulating().set(false);
    }
}
