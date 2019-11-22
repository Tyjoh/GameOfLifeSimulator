package com.tyjohtech.gol.logic.editor.event;

import com.tyjohtech.gol.util.event.Event;

public class ToolActionEvent implements Event {

    public enum Type {
        BEGIN,
        CONTINUE,
        END
    }

    private String toolName;
    private Type actionType;

    private ToolActionEvent(String toolName, Type actionType) {
        this.toolName = toolName;
        this.actionType = actionType;
    }

    public static ToolActionEvent beginAction(String toolName) {
        return new ToolActionEvent(toolName, Type.BEGIN);
    }

    public static ToolActionEvent continueAction(ToolActionEvent lastEvent) {
        return new ToolActionEvent(lastEvent.toolName, Type.CONTINUE);
    }

    public static ToolActionEvent endAction(ToolActionEvent lastEvent) {
        return new ToolActionEvent(lastEvent.toolName, Type.END);
    }

    public String getToolName() {
        return toolName;
    }

    public Type getActionType() {
        return actionType;
    }
}
