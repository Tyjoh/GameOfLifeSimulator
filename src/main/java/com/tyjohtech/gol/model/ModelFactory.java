package com.tyjohtech.gol.model;

import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;

public interface ModelFactory {

    void initialize(ModelPropertyBus propertyBus, EventBus eventBus, CommandProcessor commandProcessor);

}
