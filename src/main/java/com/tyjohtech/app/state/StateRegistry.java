package com.tyjohtech.app.state;

import java.util.HashMap;
import java.util.Map;

public class StateRegistry {

    private Map<Class<?>, Object> states = new HashMap<>();

    public <T> void registerState(Class<T> stateClass, T t) {
        states.put(stateClass, t);
    }

    public <T> T getState(Class<T> stateClass) {
        return (T) states.get(stateClass);
    }
}
