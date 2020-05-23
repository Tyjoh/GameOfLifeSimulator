package com.tyjohtech.gol.components.editor.tool;

import com.tyjohtech.app.command.CommandExecutor;
import com.tyjohtech.gol.components.editor.command.EditCommand;
import com.tyjohtech.gol.components.editor.state.*;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;

public class PencilTool implements Tool {

    private final ToolState toolState;
    private final EditState editState;
    private final EditorBoardState boardState;
    private final CommandExecutor commandExecutor;

    public PencilTool(EditorState editorState, CommandExecutor commandExecutor) {
        this.toolState = editorState.getToolState();
        this.editState = editorState.getEditState();
        this.boardState = editorState.getBoardState();
        this.commandExecutor = commandExecutor;
    }

    @Override
    public String getName() {
        return "pencil";
    }

    @Override
    public void begin() {
        editState.beginEdit();
        addChange();
    }

    @Override
    public void moved() {
        addChange();
    }

    @Override
    public void end() {
        addChange();

        Edit edit = new Edit(editState.getEdit());
        editState.endEdit();

        EditCommand editCommand = new EditCommand(edit);
        commandExecutor.execute(editCommand);
    }

    private void addChange() {
        CellPosition position = toolState.getCursorPosition();
        CellState currentState = boardState.getBoard().getState(position.getX(), position.getY());
        CellState newState = toolState.getDrawMode();

        editState.addChange(new Change(position, newState, currentState));
    }
}
