package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.gol.command.CommandExecutor;
import com.bytesmyth.gol.logic.ApplicationState;
import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.state.EditorState;

public class Editor {

    private EditorState state;

    private boolean drawingEnabled = true;
    private CommandExecutor commandExecutor;

    public Editor(EditorState state, CommandExecutor commandExecutor) {
        this.state = state;
        this.commandExecutor = commandExecutor;
    }

    public void handle(DrawModeEvent drawModeEvent) {
        DrawModeCommand command = new DrawModeCommand(drawModeEvent.getDrawMode());
        commandExecutor.execute(command);
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
            commandExecutor.execute(command);
        }
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        EditorCommand command = (state) -> state.getCursorPosition().set(cursorPosition);
        commandExecutor.execute(command);
    }
}
