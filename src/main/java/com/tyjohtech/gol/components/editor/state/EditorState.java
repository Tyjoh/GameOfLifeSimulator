package com.tyjohtech.gol.components.editor.state;

import com.tyjohtech.app.property.AbstractObservable;

public class EditorState extends AbstractObservable<EditorState> {

    private EditorBoardState boardState = new EditorBoardState();
    private ToolState toolState = new ToolState();
    private EditState editState = new EditState();
    private boolean editing = false;

    public EditorBoardState getBoardState() {
        return boardState;
    }

    public ToolState getToolState() {
        return toolState;
    }

    public EditState getEditState() {
        return editState;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;

        if (editing) {
            boardState.pushBoard();
        }
    }
}
