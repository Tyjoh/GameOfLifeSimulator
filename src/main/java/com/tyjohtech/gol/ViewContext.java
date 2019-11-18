package com.tyjohtech.gol;

import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.event.EventEmitter;

public class ViewContext {

    private EventEmitter eventEmitter;
    private ModelProvider modelProvider;


    public ViewContext(EventEmitter eventEmitter, ModelProvider modelProvider) {
        this.eventEmitter = eventEmitter;
        this.modelProvider = modelProvider;
    }

    public EventEmitter getEventEmitter() {
        return eventEmitter;
    }

    public ModelProvider getModelProvider() {
        return modelProvider;
    }
}
