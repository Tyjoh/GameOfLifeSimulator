package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.event.CursorEvent;
import com.tyjohtech.gol.logic.editor.event.DrawStateEvent;
import com.tyjohtech.gol.logic.editor.event.ToolInvokeEvent;
import com.tyjohtech.gol.logic.editor.tool.EditorTool;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoardRegion;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.command.Command;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.property.Property;

public class BoardEditor {

    private Property<CellPosition> cursor = new Property<>();
    private Property<BoardRegion> selection = new Property<>();
    private Property<Board> board = new Property<>();
    private Property<CellState> drawState = new Property<>();
    private Property<EditorTool> selectedTool = new Property<>();

    private EditorToolRegistry editorToolRegistry;
    private CommandProcessor commandProcessor;

    public BoardEditor(EditorToolRegistry editorToolRegistry, CommandProcessor commandProcessor) {
        this.editorToolRegistry = editorToolRegistry;
        this.commandProcessor = commandProcessor;
    }

    public void handle(CursorEvent event) {
        cursor.set(event.getPosition());
    }

    public void handle(DrawStateEvent drawStateEvent) {
        this.drawState.set(drawStateEvent.getDrawState());
    }

    public void handle(ToolInvokeEvent toolInvokeEvent) {
        EditorTool editorTool = editorToolRegistry.get(toolInvokeEvent.getToolName());
        Command toolCommand = editorTool.createCommand();
        commandProcessor.execute(toolCommand);
    }

    public Property<CellPosition> getCursor() {
        return cursor;
    }

    public Property<BoardRegion> getSelection() {
        return selection;
    }

    public Property<Board> getBoard() {
        return board;
    }

    public Property<CellState> getDrawState() {
        return drawState;
    }

    public Property<EditorTool> getSelectedTool() {
        return selectedTool;
    }
}
