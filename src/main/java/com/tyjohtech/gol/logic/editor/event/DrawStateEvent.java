package com.tyjohtech.gol.logic.editor.event;

import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.event.Event;

public class DrawStateEvent implements Event {
    private CellState drawState;

    public DrawStateEvent(CellState drawState) {
        this.drawState = drawState;
    }

    public CellState getDrawState() {
        return drawState;
    }
}
