package com.tyjohtech.gol.state;

import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.util.Property;

public class SimulatorState {
    private Property<Board> board = new Property<>();

    public SimulatorState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
