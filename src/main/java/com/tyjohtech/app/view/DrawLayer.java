package com.tyjohtech.app.view;

import javafx.scene.canvas.GraphicsContext;

public interface DrawLayer {

    int getLayer();

    void setOnInvalidated(InvalidationListener listener);

    void render(GraphicsContext g, int startX, int startY, int endX, int endY);

}
