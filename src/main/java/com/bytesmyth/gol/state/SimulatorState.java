package com.bytesmyth.gol.state;

import com.bytesmyth.app.observable.Property;
import com.bytesmyth.gol.model.Board;

public class SimulatorState {
    private Property<Board> board = new Property<>();

    public SimulatorState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
