package com.bytesmyth.gol.components.editor;

import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.model.CellState;

public class Change {
    private CellPosition position;
    private CellState newState;
    private CellState prevState;

    public Change(CellPosition position, CellState newState, CellState prevState) {
        this.position = position;
        this.newState = newState;
        this.prevState = prevState;
    }

    public CellPosition getPosition() {
        return position;
    }

    public CellState getNewState() {
        return newState;
    }

    public CellState getPrevState() {
        return prevState;
    }
}
