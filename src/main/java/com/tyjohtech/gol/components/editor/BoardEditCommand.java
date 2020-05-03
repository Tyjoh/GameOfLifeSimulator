package com.tyjohtech.gol.components.editor;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;

public class BoardEditCommand implements EditorCommand {

    private CellPosition position;
    private CellState drawMode;

    public BoardEditCommand(CellPosition position, CellState drawMode) {
        this.position = position;
        this.drawMode = drawMode;
    }

    @Override
    public void execute(EditorState editorState) {
        Board board = editorState.getEditorBoard().get();
        board.setState(position.getX(), position.getY(), drawMode);
        editorState.getEditorBoard().set(board);
    }
}
