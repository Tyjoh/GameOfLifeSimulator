package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolInvokeEvent;
import com.tyjohtech.gol.logic.editor.tool.EditorToolRegistry;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;

public class EditorModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        EditorToolRegistry toolRegistry = new EditorToolRegistry();
        propertyBus.publish(EditorToolRegistry.class, toolRegistry);

        EditorState editorState = new EditorState();
        propertyBus.publish(EditorState.class, editorState);

        EditorEventHandler eventHandler = new EditorEventHandler(toolRegistry, editorState, commandProcessor);
        propertyBus.publish(EditorEventHandler.class, eventHandler);

        eventBus.listenFor(CursorEvent.class, eventHandler::handle);
        eventBus.listenFor(DrawStateEvent.class, eventHandler::handle);
        eventBus.listenFor(ToolInvokeEvent.class, eventHandler::handle);

        editorState.getBoard().set(new BoundedBoard(45, 37));
        editorState.getDrawState().set(CellState.ALIVE);
    }
}
