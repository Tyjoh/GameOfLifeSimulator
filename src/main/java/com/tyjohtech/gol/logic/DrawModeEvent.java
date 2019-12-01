package com.tyjohtech.gol.logic;

import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.event.Event;

public class DrawModeEvent implements Event {

    private CellState newDrawMode;

    public DrawModeEvent(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    public CellState getDrawMode() {
        return newDrawMode;
    }
}
