package com.tyjohtech.gol;

import com.tyjohtech.gol.util.event.EventEmitter;
import com.tyjohtech.gol.util.property.ModelPropertyBus;

public class ViewContext {

    private EventEmitter eventEmitter;
    private ModelPropertyBus modelPropertyBus;


    public ViewContext(EventEmitter eventEmitter, ModelPropertyBus modelPropertyBus) {
        this.eventEmitter = eventEmitter;
        this.modelPropertyBus = modelPropertyBus;
    }

    public EventEmitter getEventEmitter() {
        return eventEmitter;
    }

    public ModelPropertyBus getModelPropertyBus() {
        return modelPropertyBus;
    }
}
