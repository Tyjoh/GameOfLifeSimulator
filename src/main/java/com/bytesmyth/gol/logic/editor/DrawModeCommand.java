package com.bytesmyth.gol.logic.editor;

import com.bytesmyth.gol.model.CellState;
import com.bytesmyth.gol.state.EditorState;

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
