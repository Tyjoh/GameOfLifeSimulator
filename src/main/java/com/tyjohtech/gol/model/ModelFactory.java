package com.tyjohtech.gol.model;

import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;

public interface ModelFactory {

    void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor);

}
