package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.model.Simulator;
import com.tyjohtech.gol.model.SimulatorEvent;

public class SimulatorEventHandler {

    private Simulator simulator;

    public SimulatorEventHandler(Simulator simulator) {
        this.simulator = simulator;
    }

    public void handle(SimulatorEvent event) {
        switch (event.getType()) {
            case START:
                simulator.start();
                break;
            case STOP:
                simulator.stop();
                break;
            case STEP:
                simulator.doStep();
                break;
            case RESET:
                simulator.reset();
                break;
        }
    }

}
