package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;
import com.tyjohtech.gol.logic.editor.event.ToolSelectEvent;
import com.tyjohtech.gol.logic.editor.tool.EditorTool;
import com.tyjohtech.gol.logic.editor.tool.EditorToolRegistry;

public class EditorEventHandler {

    private EditorToolRegistry toolRegistry;
    private EditorState editorState;

    public EditorEventHandler(EditorToolRegistry toolRegistry, EditorState editorState) {
        this.toolRegistry = toolRegistry;
        this.editorState = editorState;
    }

    public void handle(CursorEvent event) {
        editorState.getCursor().set(event.getPosition());
    }

    public void handle(DrawStateEvent drawStateEvent) {
        editorState.getDrawState().set(drawStateEvent.getDrawState());
    }

    public void handle(ToolSelectEvent toolSelectEvent) {
        editorState.getSelectedTool().set(toolSelectEvent.getToolName());
    }

    public void handle(ToolActionEvent toolActionEvent) {
        EditorTool editorTool = toolRegistry.get(toolActionEvent.getToolName());
        editorTool.handle(toolActionEvent);
    }

}
