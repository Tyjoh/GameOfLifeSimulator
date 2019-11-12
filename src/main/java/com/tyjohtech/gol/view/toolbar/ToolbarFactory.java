package com.tyjohtech.gol.view.toolbar;

import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;

public class ToolbarFactory implements ViewFactory {

    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.TOOLBAR;
    }

    @Override
    public Node buildView(ModelPropertyBus propertyProvider, EventBus eventBus) {
        ToolbarViewModel toolbarViewModel = new ToolbarViewModel();
        return new Toolbar(toolbarViewModel, eventBus);
    }
}
