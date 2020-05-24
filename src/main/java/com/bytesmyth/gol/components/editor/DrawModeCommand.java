package com.bytesmyth.gol.components.editor;

import com.bytesmyth.gol.model.CellState;

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
