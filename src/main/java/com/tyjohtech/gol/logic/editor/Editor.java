package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.logic.ApplicationState;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.state.EditorState;

public class Editor {

    private EditorState state;

    private boolean drawingEnabled = true;

    public Editor(EditorState state) {
        this.state = state;
    }

    public void handle(DrawModeEvent drawModeEvent) {
        DrawModeCommand command = new DrawModeCommand(drawModeEvent.getDrawMode());
        command.execute(state);
    }

    public void handle(BoardEvent boardEvent) {
        switch (boardEvent.getEventType()) {
            case PRESSED:
                boardPressed(boardEvent.getCursorPosition());
                break;
            case CURSOR_MOVED:
                cursorPositionChanged(boardEvent.getCursorPosition());
                break;
        }
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.EDITING) {
            drawingEnabled = true;
        } else {
            drawingEnabled = false;
        }
    }

    private void boardPressed(CellPosition cursorPosition) {
        cursorPositionChanged(cursorPosition);
        if (drawingEnabled) {
            BoardEditCommand command = new BoardEditCommand(cursorPosition, state.getDrawMode().get());
            command.execute(this.state);
        }
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        EditorCommand command = (state) -> {
            state.getCursorPosition().set(cursorPosition);
        };
        command.execute(this.state);
    }
}
