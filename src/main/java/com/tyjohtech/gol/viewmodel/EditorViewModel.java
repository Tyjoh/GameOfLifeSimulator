package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.Property;

public class EditorViewModel {

    private Property<CellState> drawMode = new Property<>(CellState.ALIVE);
    private Property<CellPosition> cursorPosition = new Property<>();

    private BoardViewModel boardViewModel;
    private Board editorBoard;
    private boolean drawingEnabled = true;

    public EditorViewModel(BoardViewModel boardViewModel, Board initialBoard) {
        this.boardViewModel = boardViewModel;
        this.editorBoard = initialBoard;
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
            this.boardViewModel.getBoard().set(editorBoard);
        } else {
            drawingEnabled = false;
        }
    }

    private void boardPressed(CellPosition cursorPosition) {
        this.cursorPosition.set(cursorPosition);
        if (drawingEnabled) {
            this.editorBoard.setState(cursorPosition.getX(), cursorPosition.getY(), drawMode.get());
            this.boardViewModel.getBoard().set(this.editorBoard);
        }
    }

    public Property<CellState> getDrawMode() {
        return drawMode;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }

    public Board getBoard() {
        return editorBoard;
    }
}
