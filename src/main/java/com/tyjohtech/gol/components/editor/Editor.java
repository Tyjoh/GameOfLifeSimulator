package com.tyjohtech.gol.components.editor;

import com.tyjohtech.app.command.CommandExecutor;
import com.tyjohtech.gol.components.editor.command.DrawModeCommand;
import com.tyjohtech.gol.components.editor.state.EditState;
import com.tyjohtech.gol.components.editor.state.EditorState;
import com.tyjohtech.gol.components.editor.state.ToolState;
import com.tyjohtech.gol.components.editor.tool.Tool;
import com.tyjohtech.gol.components.simulator.SimulatorEvent;
import com.tyjohtech.gol.model.CellPosition;

import java.util.Map;

public class Editor {

    private final ToolState toolState;
    private final EditState editState;
    private final EditorState editorState;

    private Map<String, Tool> tools;

    private boolean drawingEnabled = true;
    private CommandExecutor commandExecutor;

    public Editor(EditorState state, Map<String, Tool> tools, CommandExecutor commandExecutor) {
        this.toolState = state.getToolState();
        this.editState = state.getEditState();
        this.editorState = state;
        this.tools = tools;
        this.commandExecutor = commandExecutor;
    }

    public void handle(DrawModeEvent drawModeEvent) {
        DrawModeCommand command = new DrawModeCommand(drawModeEvent.getDrawMode());
        commandExecutor.execute(command);
    }

    public void handle(BoardEvent boardEvent) {
        cursorPositionChanged(boardEvent.getCursorPosition());

        switch (boardEvent.getEventType()) {
            case PRESSED:
                if (drawingEnabled)
                    boardPressed();
                break;
            case CURSOR_MOVED:
                cursorPositionChanged(boardEvent.getCursorPosition());
                break;
            case RELEASED:
                if (drawingEnabled)
                    boardReleased();
                break;
        }
    }

    public void handleSimulatorEvent(SimulatorEvent event) {
        if (event.getEventType() == SimulatorEvent.Type.RESET) {
            editorState.setEditing(true);
        } else if (event.getEventType() == SimulatorEvent.Type.START || event.getEventType() == SimulatorEvent.Type.STEP) {
            editorState.setEditing(false);
        }
    }

    private void boardPressed() {
        String currentTool = toolState.getCurrentTool();
        Tool tool = tools.get(currentTool);
        tool.begin();
    }

    private void boardReleased() {
        String currentTool = toolState.getCurrentTool();
        Tool tool = tools.get(currentTool);
        tool.end();
    }

    private void cursorPositionChanged(CellPosition cursorPosition) {
        toolState.setCursorPosition(cursorPosition);

        if (editState.isInProgress()) {
            String currentTool = toolState.getCurrentTool();
            Tool tool = tools.get(currentTool);
            tool.moved();
        }
    }
}
