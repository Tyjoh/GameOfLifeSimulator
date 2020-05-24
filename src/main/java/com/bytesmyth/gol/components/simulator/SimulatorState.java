package com.bytesmyth.gol.components.simulator;

import com.bytesmyth.app.observable.Property;
import com.bytesmyth.gol.model.Board;

public class SimulatorState {
    private Property<Board> board = new Property<>();

    private Property<Boolean> simulating = new Property<>(false);

    public SimulatorState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }

    public Property<Boolean> getSimulating() {
        return simulating;
    }
}
