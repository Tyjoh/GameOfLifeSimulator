package com.tyjohtech.gol.view.settings;

import com.tyjohtech.gol.logic.simulator.SimulationConfig;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;

public class SimulationSettingsFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.RIGHT;
    }

    @Override
    public Node buildView(ModelProvider propertyProvider, EventBus eventBus) {
        SimulationConfig simulationConfig = propertyProvider.get(SimulationConfig.class);

        SettingsViewModel settingsViewModel = new SettingsViewModel();
        settingsViewModel.getIterationCount().bindTo(simulationConfig.getProperty("iterationCount"));
        settingsViewModel.getLimitIterations().bindTo(simulationConfig.getProperty("limitIterations"));
        settingsViewModel.getSimulationSpeed().bindTo(simulationConfig.getProperty("simulationSpeed"));

        SettingsPane settingsPane = new SettingsPane(settingsViewModel, eventBus);
        settingsPane.setPrefWidth(300);

        return settingsPane;
    }
}
