package com.bytesmyth.gol.components.editor;

import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.CellPosition;

public class BoardEditCommand implements UndoableEditorCommand {

    private Edit edit;

    public BoardEditCommand(Edit edit) {
        this.edit = new Edit(edit);
    }

    @Override
    public void execute(EditorState editorState) {
        Board board = editorState.getEditorBoard().get();

        for (Change change : edit) {
            CellPosition position = change.getPosition();
            board.setState(position.getX(), position.getY(), change.getNewState());
        }

        editorState.getEditorBoard().set(board);
    }

    @Override
    public void undo(EditorState editorState) {
        Board board = editorState.getEditorBoard().get();

        for (Change change : edit) {
            CellPosition position = change.getPosition();
            board.setState(position.getX(), position.getY(), change.getPrevState());
        }

        editorState.getEditorBoard().set(board);
    }
}
