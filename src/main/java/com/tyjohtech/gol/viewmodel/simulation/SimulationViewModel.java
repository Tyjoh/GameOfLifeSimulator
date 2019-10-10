package com.tyjohtech.gol.viewmodel.simulation;

import com.tyjohtech.gol.Simulator;
import com.tyjohtech.gol.viewmodel.application.ApplicationState;
import com.tyjohtech.gol.viewmodel.application.ApplicationViewModel;

public class SimulationViewModel {

    private Simulator simulator;
    private ApplicationViewModel applicationViewModel;

    public SimulationViewModel(Simulator simulator, ApplicationViewModel applicationViewModel) {
        this.simulator = simulator;
        this.applicationViewModel = applicationViewModel;
    }

    public void startSimulation() {
        enterSimulatingState();
        this.simulator.start();
    }

    public void stopSimulation() {
        this.simulator.stop();
    }

    public void stepSimulation() {
        enterSimulatingState();
        this.simulator.step();
    }

    private void enterSimulatingState() {
        if (applicationViewModel.getApplicationState() != ApplicationState.SIMULATING) {
            this.applicationViewModel.setApplicationState(ApplicationState.SIMULATING);
        }
    }

    public void reset() {
        this.simulator.stop();
        this.applicationViewModel.setApplicationState(ApplicationState.EDITING);
    }

}
