package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolInvokeEvent;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;

public class EditorModelFactory implements ModelFactory {

    @Override
    public void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        EditorToolRegistry editorToolRegistry = new EditorToolRegistry();
        propertyBus.publish(EditorToolRegistry.class, editorToolRegistry);

        BoardEditor boardEditor = new BoardEditor(editorToolRegistry, commandProcessor);
        propertyBus.publish(BoardEditor.class, boardEditor);
        eventBus.listenFor(CursorEvent.class, boardEditor::handle);
        eventBus.listenFor(DrawStateEvent.class, boardEditor::handle);
        eventBus.listenFor(ToolInvokeEvent.class, boardEditor::handle);

        boardEditor.getBoard().set(new BoundedBoard(45, 37));
        boardEditor.getDrawState().set(CellState.ALIVE);
    }
}
