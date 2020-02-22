package com.tyjohtech.gol.logic.editor;

import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.state.EditorState;

public class DrawModeCommand implements EditorCommand {

    private CellState newDrawMode;

    public DrawModeCommand(CellState newDrawMode) {
        this.newDrawMode = newDrawMode;
    }

    @Override
    public void execute(EditorState editorState) {
        editorState.getDrawMode().set(newDrawMode);
    }
}
