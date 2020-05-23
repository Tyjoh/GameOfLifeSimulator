package com.tyjohtech.gol.components.editor.command;

import com.tyjohtech.gol.components.editor.state.EditorState;
import com.tyjohtech.gol.model.CellState;

public class DrawModeCommand implements EditorCommand {

    private CellState newDrawMode;

    public DrawModeCommand(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    @Override
    public void execute(EditorState editorState) {
        editorState.getToolState().setDrawMode(newDrawMode);
    }
}
