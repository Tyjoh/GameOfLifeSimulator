package com.tyjohtech.gol.model;

import com.tyjohtech.gol.model.board.Board;
import com.tyjohtech.gol.model.board.BoundedBoard;
import com.tyjohtech.gol.model.board.CellState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoundedBoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new BoundedBoard(5, 3);
    }

    @Test
    void copy_sameSizeAsOriginal() {
        Board copy = board.copy();

        assertEquals(5, copy.getWidth());
        assertEquals(3, copy.getHeight());
    }

    @Test
    void copy_deepCopy() {
        Board copy = board.copy();

        copy.setState(3, 2, CellState.ALIVE);

        assertEquals(CellState.ALIVE, copy.getState(3, 2));
        assertEquals(CellState.DEAD, board.getState(3, 2));
    }

    @Test
    void copy_contentsAreTheSame() {
        board.setState(0, 0, CellState.ALIVE);
        board.setState(0, 1, CellState.ALIVE);
        board.setState(0, 2, CellState.ALIVE);

        Board copy = board.copy();

        for (int x = 0; x < copy.getWidth(); x++) {
            for (int y = 0; y < copy.getHeight(); y++) {
                assertEquals(board.getState(x, y), copy.getState(x, y));
            }
        }
    }

    @Test
    void setState_getState_setAndGetDontFailOutOfBounds() {
        board.setState(-1, 0, CellState.ALIVE);
        board.setState(5, 0, CellState.ALIVE);
        board.setState(0, -1, CellState.ALIVE);
        board.setState(0, 3, CellState.ALIVE);

        board.getState(-1, 0);
        board.getState(5, 0);
        board.getState(0, -1);
        board.getState(0, 3);
    }

    @Test
    void setState_getState_returnsUpdatedResult() {
        board.setState(4, 1, CellState.ALIVE);

        assertEquals(CellState.ALIVE, board.getState(4, 1));
    }
}