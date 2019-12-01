package com.tyjohtech.gol.logic;

import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.util.event.Event;

public class BoardEvent implements Event {

    public enum Type {
        CURSOR_MOVED,
        PRESSED
    }

    private Type eventType;
    private CellPosition cursorPosition;

    public BoardEvent(Type eventType, CellPosition cursorPosition) {
        this.eventType = eventType;
        this.cursorPosition = cursorPosition;
    }

    public Type getEventType() {
        return eventType;
    }

    public CellPosition getCursorPosition() {
        return cursorPosition;
    }
}
