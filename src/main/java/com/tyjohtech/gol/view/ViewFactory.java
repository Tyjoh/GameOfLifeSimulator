package com.tyjohtech.gol.view;

import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.event.EventBus;
import javafx.scene.Node;

public interface ViewFactory {

    MainViewPosition getMainViewPosition();

    Node buildView(ModelProvider propertyProvider, EventBus eventBus);

}
