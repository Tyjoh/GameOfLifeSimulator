package com.tyjohtech.gol.logic;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.Property;

public class Editor {

    private Property<CellState> drawMode = new Property<>(CellState.ALIVE);
    private Property<CellPosition> cursorPosition = new Property<>();
    private Property<Board> editorBoard = new Property<>();

    private boolean drawingEnabled = true;

    public Editor(Board initialBoard) {
        this.editorBoard.set(initialBoard);
    }

    public void handle(DrawModeEvent drawModeEvent) {
        this.drawMode.set(drawModeEvent.getDrawMode());
    }

    public void handle(BoardEvent boardEvent) {
        switch (boardEvent.getEventType()) {
            case PRESSED:
                boardPressed(boardEvent.getCursorPosition());
                break;
            case CURSOR_MOVED:
                cursorPosition.set(boardEvent.getCursorPosition());
                break;
        }
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.EDITING) {
            drawingEnabled = true;
            this.editorBoard.set(this.editorBoard.get());
        } else {
            drawingEnabled = false;
        }
    }

    private void boardPressed(CellPosition cursorPosition) {
        this.cursorPosition.set(cursorPosition);
        if (drawingEnabled) {
            Board board = this.editorBoard.get();
            board.setState(cursorPosition.getX(), cursorPosition.getY(), drawMode.get());
            this.editorBoard.set(board);
        }
    }

    public Property<CellState> getDrawMode() {
        return drawMode;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Property<Board> getBoard() {
        return editorBoard;
    }
}
