package com.tyjohtech.gol.components.simulator;

import com.tyjohtech.app.property.AbstractObservable;
import com.tyjohtech.gol.model.Board;

public class SimulatorState extends AbstractObservable<SimulatorState> {

    private Board board;
    private boolean simulating = false;

    public SimulatorState(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
        notifyListeners(this);
    }

    public void setSimulating(boolean simulating) {
        this.simulating = simulating;
    }

    public boolean isSimulating() {
        return simulating;
    }
}
