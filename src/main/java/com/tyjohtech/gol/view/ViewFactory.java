package com.tyjohtech.gol.view;

import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;
import javafx.scene.Node;

public interface ViewFactory {

    MainViewPosition getMainViewPosition();

    Node buildView(ModelProvider propertyProvider, EventBus eventBus);

}
