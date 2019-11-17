package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.logic.editor.EditorToolRegistry;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;
import com.tyjohtech.gol.util.property.ModelProvider;
import com.tyjohtech.gol.util.property.Property;

public class ToolModelFactory implements ModelFactory {
    @Override
    public void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        EditorToolRegistry toolRegistry = propertyBus.get(EditorToolRegistry.class);
        BoardEditor boardEditor = propertyBus.get(BoardEditor.class);

        Property<Board> editorBoard = boardEditor.getBoard();
        Property<CellPosition> editorCursor = boardEditor.getCursor();
        Property<CellState> editorDrawState = boardEditor.getDrawState();

        BrushTool brushTool = new BrushTool(editorBoard, editorCursor, editorDrawState);
        eventBus.listenFor(BrushConfigEvent.class, brushTool::handle);
        toolRegistry.registerTool(brushTool);

        PencilTool pencilTool = new PencilTool(editorBoard, editorCursor, editorDrawState);
        toolRegistry.registerTool(pencilTool);

        boardEditor.getSelectedTool().set(brushTool);
    }
}
