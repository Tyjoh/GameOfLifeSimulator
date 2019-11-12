package com.tyjohtech.gol.view;

import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;
import javafx.scene.Node;

public interface ViewFactory {

    MainViewPosition getMainViewPosition();

    Node buildView(ModelPropertyBus propertyProvider, EventBus eventBus);

}
