package com.tyjohtech.gol.view.settings;

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
        SettingsPane settingsPane = new SettingsPane(eventBus);
        settingsPane.setPrefWidth(300);
        return settingsPane;
    }
}
