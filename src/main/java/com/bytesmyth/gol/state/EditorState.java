package com.bytesmyth.gol.state;

import com.bytesmyth.app.observable.Property;
import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.model.CellState;

public class EditorState {

    private Property<CellState> drawMode = new Property<>(CellState.ALIVE);
    private Property<CellPosition> cursorPosition = new Property<>();
    private Property<Board> editorBoard = new Property<>();

    public EditorState(Board board) {
        editorBoard.set(board);
    }

    public Property<CellState> getDrawMode() {
        return drawMode;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Property<Board> getEditorBoard() {
        return editorBoard;
    }
}
