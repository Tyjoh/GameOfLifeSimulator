package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;

import java.util.Objects;

public class StrokePoint {
    private CellPosition position;
    private CellState newState;
    private CellState prevState;

    public StrokePoint(CellPosition position, CellState newState, CellState prevState) {
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
        StrokePoint that = (StrokePoint) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
