package com.tyjohtech.gol.logic.editor.event;

import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.event.Event;

import java.util.Objects;

public class CursorEvent implements Event {

    private CellPosition position;

    public CursorEvent(CellPosition position) {
        this.position = position;
    }

    public CellPosition getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursorEvent that = (CursorEvent) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
