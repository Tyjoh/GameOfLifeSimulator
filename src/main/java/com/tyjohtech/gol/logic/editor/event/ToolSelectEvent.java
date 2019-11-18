package com.tyjohtech.gol.logic.editor.event;

import com.tyjohtech.gol.util.event.Event;

public class ToolSelectEvent implements Event {

    private String toolName;

    public ToolSelectEvent(String toolName) {
        this.toolName = toolName;
    }

    public String getToolName() {
        return toolName;
    }
}
