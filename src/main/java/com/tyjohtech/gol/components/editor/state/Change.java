package com.tyjohtech.gol.components.editor.state;

import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Change change = (Change) o;
        return Objects.equals(position, change.position) &&
                newState == change.newState &&
                prevState == change.prevState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, newState, prevState);
    }
}
