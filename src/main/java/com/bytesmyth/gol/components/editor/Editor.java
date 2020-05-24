package com.bytesmyth.gol.components.editor;

import com.bytesmyth.app.command.CommandExecutor;
import com.bytesmyth.gol.components.simulator.SimulatorEvent;
import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.model.CellState;

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

    public void handleSimulatorEvent(SimulatorEvent event) {
        if (event.getEventType() == SimulatorEvent.Type.RESET) {
            drawingEnabled = true;
        } else if (event.getEventType() == SimulatorEvent.Type.START || event.getEventType() == SimulatorEvent.Type.STEP) {
            drawingEnabled = false;
        }
    }

    private void boardPressed(CellPosition cursorPosition) {
        cursorPositionChanged(cursorPosition);
        if (drawingEnabled) {
            CellState currentState = this.state.getEditorBoard().get().getState(cursorPosition.getX(), cursorPosition.getY());
            CellState newState = this.state.getDrawMode().get();

            if (currentState != newState) {
                BoardEditCommand command = new BoardEditCommand(cursorPosition, newState, currentState);
                commandExecutor.execute(command);
            }
        }
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        EditorCommand command = (state) -> state.getCursorPosition().set(cursorPosition);
        commandExecutor.execute(command);
    }
}
