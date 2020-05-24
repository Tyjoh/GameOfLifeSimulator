package com.bytesmyth.gol.components.editor;

import com.bytesmyth.app.event.Event;
import com.bytesmyth.gol.model.CellPosition;

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
