package com.tyjohtech.gol.components.editor.command;

import com.tyjohtech.gol.components.editor.state.Change;
import com.tyjohtech.gol.components.editor.state.Edit;
import com.tyjohtech.gol.components.editor.state.EditorState;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;

public class EditCommand implements EditorCommand {

    private Edit edit;

    public EditCommand(Edit edit) {
        this.edit = edit;
    }

    @Override
    public void execute(EditorState editorState) {
        Board board = editorState.getBoardState().getBoard();
        for (Change change : edit) {
            CellPosition pos = change.getPosition();
            board.setState(pos.getX(), pos.getY(), change.getNewState());
        }
        editorState.getBoardState().setBoard(board);
    }
}
