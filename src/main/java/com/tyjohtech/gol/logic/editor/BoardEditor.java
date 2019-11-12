package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.editor.tool.EditorTool;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.CellPosition;
import com.tyjohtech.gol.model.board.CellRegion;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.util.property.Property;

public class BoardEditor {

    private Property<CellPosition> cursor = new Property<>();
    private Property<CellRegion> selection = new Property<>();
    private Property<Board> board = new Property<>();
    private Property<EditorTool> tool = new Property<>();
    private Property<CellState> drawState = new Property<>();

    public void handle(BoardEvent boardEvent) {
        cursor.set(boardEvent.getPosition());
        tool.get().handle(boardEvent, this);
    }

    public Property<CellPosition> getCursor() {
        return cursor;
    }

    public Property<CellRegion> getSelection() {
        return selection;
    }

    public Property<Board> getBoard() {
        return board;
    }

    public Property<EditorTool> getTool() {
        return tool;
    }

    public Property<CellState> getDrawState() {
        return drawState;
    }
}
