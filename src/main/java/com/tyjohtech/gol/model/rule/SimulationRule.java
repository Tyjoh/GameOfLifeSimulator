package com.tyjohtech.gol.model.rule;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.CellState;

public interface SimulationRule {
    CellState getNextState(int x, int y, Board board);
}
