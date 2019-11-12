package com.tyjohtech.gol.view.settings;

import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;
import com.tyjohtech.gol.view.KeyValuePane;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SimulationSettingsFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.RIGHT;
    }

    @Override
    public Node buildView(ModelPropertyBus propertyProvider, EventBus eventBus) {

        KeyValuePane settingsPane = new KeyValuePane();
        settingsPane.setPrefWidth(300);
        settingsPane.add("Setting 1", new Slider(1, 10, 3));
        settingsPane.add("Setting 2", new CheckBox());
        settingsPane.add("Setting 3", new TextField("4352"));

        return settingsPane;
    }
}
