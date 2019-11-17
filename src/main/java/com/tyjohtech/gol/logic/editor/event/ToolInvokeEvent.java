package com.tyjohtech.gol.logic.editor.event;

import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.util.event.Event;

public class ToolInvokeEvent implements Event {
    private String toolName;
    private CellPosition cursorPosition;

    public ToolInvokeEvent(String toolName, CellPosition cursorPosition) {
        this.toolName = toolName;
        this.cursorPosition = cursorPosition;
    }

    public String getToolName() {
        return toolName;
    }

    public CellPosition getCursorPosition() {
        return cursorPosition;
    }
}
