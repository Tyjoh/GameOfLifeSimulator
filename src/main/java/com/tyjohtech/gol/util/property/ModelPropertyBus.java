package com.tyjohtech.gol.util.property;

import java.util.HashMap;
import java.util.Map;

public class ModelPropertyBus {

    private Map<Class, PropertyMap> publishedClasses = new HashMap<>();

    public <T> void publish(String propertyName, Class<?> modelClass, Property<T> property) {
        if (!publishedClasses.containsKey(modelClass)) {
            publishedClasses.put(modelClass, new PropertyMap());
        }

        PropertyMap propertyMap = publishedClasses.get(modelClass);
        propertyMap.add(propertyName, property);
    }

    public <T> Property<T> get(Class modelClass, String propertyName) {
        PropertyMap propertyMap = publishedClasses.get(modelClass);
        return propertyMap.get(propertyName);
    }

    private static class PropertyMap {
        private Map<String, Property> classProperties = new HashMap<>();

        public void add(String name, Property property) {
            classProperties.put(name, property);
        }

        public Property get(String name) {
            return classProperties.get(name);
        }
    }

}
