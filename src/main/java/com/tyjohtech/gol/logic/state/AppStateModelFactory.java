package com.tyjohtech.gol.logic.state;

import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;

public class AppStateModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        AppStateRepository appStateRepo = new AppStateRepository(AppState.EDITING);
        eventBus.listenFor(AppStateEvent.class, appStateRepo::handle);
        propertyBus.publish(AppStateRepository.class, appStateRepo);
    }
}
