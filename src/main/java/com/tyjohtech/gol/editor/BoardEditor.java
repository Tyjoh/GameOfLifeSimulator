package com.tyjohtech.gol.editor;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellRegion;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.property.Property;

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
