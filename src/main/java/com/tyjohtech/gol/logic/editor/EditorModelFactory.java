package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.tool.PencilTool;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelPropertyBus;

public class EditorModelFactory implements ModelFactory {

    private BoardEditor boardEditor;

    @Override
    public void initialize(ModelPropertyBus propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        boardEditor = new BoardEditor();
        eventBus.listenFor(BoardEvent.class, boardEditor::handle);
        boardEditor.getBoard().set(new BoundedBoard(45, 37));
        boardEditor.getDrawState().set(CellState.ALIVE);
        boardEditor.getTool().set(new PencilTool(commandProcessor));

        propertyBus.publish("board", BoardEditor.class, boardEditor.getBoard());
        propertyBus.publish("selection", BoardEditor.class, boardEditor.getSelection());
        propertyBus.publish("cursor", BoardEditor.class, boardEditor.getCursor());
        propertyBus.publish("tool", BoardEditor.class, boardEditor.getTool());
        propertyBus.publish("drawState", BoardEditor.class, boardEditor.getDrawState());
    }
}
