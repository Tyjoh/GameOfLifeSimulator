package com.bytesmyth.gol.logic.board;

import com.bytesmyth.app.observable.Property;
import com.bytesmyth.gol.model.Board;

public class BoardState {

    private Property<Board> board = new Property<Board>();

    public BoardState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
