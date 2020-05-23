package com.tyjohtech.gol.components.editor;

import com.tyjohtech.app.event.Event;
import com.tyjohtech.gol.model.CellPosition;

public class BoardEvent implements Event {

    public enum Type {
        CURSOR_MOVED,
        PRESSED,
        RELEASED
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
