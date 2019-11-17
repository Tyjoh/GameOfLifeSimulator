package com.tyjohtech.gol.util.property;

import java.util.HashMap;
import java.util.Map;

public class ModelProvider {

    private Map<Class, Object> modelObjects = new HashMap<>();

    public <T> void publish(Class<T> modelClass, T object) {
        if (modelObjects.containsKey(modelClass)) {
            throw new RuntimeException("Class has already been bound");
        }

        modelObjects.put(modelClass, object);
    }

    public <T> T get(Class<T> modelClass) {
        Object object = modelObjects.get(modelClass);

        if (modelClass.isAssignableFrom(object.getClass())) {
            return (T) object;
        } else {
            throw new RuntimeException("Object is not of correct type");
        }
    }

}
