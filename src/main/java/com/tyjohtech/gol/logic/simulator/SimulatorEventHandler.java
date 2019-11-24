package com.tyjohtech.gol.logic.simulator;

import com.tyjohtech.gol.logic.simulator.config.SimulationConfigEvent;
import com.tyjohtech.gol.logic.state.AppState;
import com.tyjohtech.gol.logic.state.AppStateEvent;
import com.tyjohtech.gol.util.event.EventBus;

public class SimulatorEventHandler {

    private Simulator simulator;
    private EventBus eventBus;

    public SimulatorEventHandler(Simulator simulator, EventBus eventBus) {
        this.simulator = simulator;
        this.eventBus = eventBus;
    }

    public void handle(SimulatorEvent event) {
        switch (event.getType()) {
            case START:
                eventBus.emit(new AppStateEvent(AppState.SIMULATING));
                simulator.start();
                break;
            case STOP:
                simulator.stop();
                break;
            case STEP:
                eventBus.emit(new AppStateEvent(AppState.SIMULATING));
                simulator.doStep();
                break;
            case RESET:
                eventBus.emit(new AppStateEvent(AppState.EDITING));
                simulator.reset();
                break;
        }
    }

    public void handle(SimulationConfigEvent simulationConfigEvent) {

    }

}
