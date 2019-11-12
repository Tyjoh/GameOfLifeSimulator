package com.tyjohtech.gol;

import com.tyjohtech.gol.model.Simulation;
import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import com.tyjohtech.gol.model.rule.StandardRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    @Test
    void simulatesEntireBounds() {
        Board board = new BoundedBoard(5, 3);
        board.setState(0, 0, CellState.ALIVE);
        board.setState(4, 0, CellState.ALIVE);
        board.setState(4, 2, CellState.ALIVE);
        board.setState(0, 2, CellState.ALIVE);

        Simulation simulation = new Simulation(board, new StandardRule());

        simulation.step();

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                assertEquals(CellState.DEAD, simulation.getBoard().getState(x, y));
            }
        }
    }
}