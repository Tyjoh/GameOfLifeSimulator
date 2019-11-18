package com.tyjohtech.gol.view.infobar;

import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.view.MainViewPosition;
import com.tyjohtech.gol.view.ViewFactory;
import javafx.scene.Node;

public class InfoBarFactory implements ViewFactory {
    @Override
    public MainViewPosition getMainViewPosition() {
        return MainViewPosition.BOTTOM;
    }

    @Override
    public Node buildView(ModelProvider propertyProvider, EventBus eventBus) {
        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();
        return new InfoBar(infoBarViewModel);
    }
}
