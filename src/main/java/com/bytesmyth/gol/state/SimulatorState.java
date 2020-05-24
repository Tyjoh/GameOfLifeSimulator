package com.bytesmyth.gol.state;

import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.util.Property;

public class SimulatorState {
    private Property<Board> board = new Property<>();

    public SimulatorState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
