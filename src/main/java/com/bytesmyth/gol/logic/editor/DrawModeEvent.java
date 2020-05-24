package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.gol.model.CellState;
import com.bytesmyth.gol.util.event.Event;

public class DrawModeEvent implements Event {

    private CellState newDrawMode;

    public DrawModeEvent(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    public CellState getDrawMode() {
        return newDrawMode;
    }
}
