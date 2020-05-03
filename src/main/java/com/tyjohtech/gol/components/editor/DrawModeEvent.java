package com.tyjohtech.gol.components.editor;

import com.tyjohtech.app.event.Event;
import com.tyjohtech.gol.model.CellState;

public class DrawModeEvent implements Event {

    private CellState newDrawMode;

    public DrawModeEvent(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    public CellState getDrawMode() {
        return newDrawMode;
    }
}
