package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolInvokeEvent;
import com.tyjohtech.gol.logic.editor.event.ToolSelectEvent;
import com.tyjohtech.gol.logic.editor.tool.EditorTool;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.command.CommandProcessor;

public class EditorEventHandler {

    private EditorToolRegistry toolRegistry;
    private EditorState editorState;
    private CommandProcessor commandProcessor;

    public EditorEventHandler(EditorToolRegistry toolRegistry, EditorState editorState, CommandProcessor commandProcessor) {
        this.toolRegistry = toolRegistry;
        this.editorState = editorState;
        this.commandProcessor = commandProcessor;
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

    public void handle(ToolInvokeEvent toolInvokeEvent) {
        EditorTool editorTool = toolRegistry.get(toolInvokeEvent.getToolName());
        Command toolCommand = editorTool.createCommand();
        commandProcessor.execute(toolCommand);
    }

}
