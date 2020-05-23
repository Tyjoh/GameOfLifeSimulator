package com.tyjohtech.app.view;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDrawLayer implements DrawLayer {

    private final int drawLayer;

    private List<InvalidationListener> listeners = new LinkedList<>();

    public AbstractDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }

    @Override
    public int getLayer() {
        return drawLayer;
    }

    @Override
    public void setOnInvalidated(InvalidationListener listener) {
        listeners.add(listener);
    }

    protected void invalidate() {
        listeners.forEach(InvalidationListener::onInvalidated);
    }
}
