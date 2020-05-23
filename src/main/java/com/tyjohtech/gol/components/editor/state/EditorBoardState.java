package com.tyjohtech.gol.components.editor.state;

import com.tyjohtech.app.property.AbstractObservable;
import com.tyjohtech.gol.model.Board;

public class EditorBoardState extends AbstractObservable<EditorBoardState> {

    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
        notifyListeners(this);
    }

    public void pushBoard() {
        notifyListeners(this);
    }
}
