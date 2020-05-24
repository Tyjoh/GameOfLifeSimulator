package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.app.event.Event;
import com.bytesmyth.gol.model.CellState;

public class DrawModeEvent implements Event {

    private CellState newDrawMode;

    public DrawModeEvent(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    public CellState getDrawMode() {
        return newDrawMode;
    }
}
