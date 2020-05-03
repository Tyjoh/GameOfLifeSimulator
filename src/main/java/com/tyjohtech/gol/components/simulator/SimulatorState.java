package com.tyjohtech.gol.components.simulator;

import com.tyjohtech.app.property.Property;
import com.tyjohtech.gol.model.Board;

public class SimulatorState {
    private Property<Board> board = new Property<>();

    public SimulatorState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
