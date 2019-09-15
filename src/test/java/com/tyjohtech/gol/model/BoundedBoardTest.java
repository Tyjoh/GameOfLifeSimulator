package com.tyjohtech.gol.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoundedBoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new BoundedBoard(5, 3);
    }

    @Test
    void copy_notSameInstance() {
        Board copy = board.copy();
        assertNotEquals(board, copy);
    }

    @Test
    void copy_doesNotChangOriginal() {
        board.setState(2, 2, CellState.ALIVE);
        Board copy = board.copy();

        copy.setState(2, 2, CellState.DEAD);

        assertNotEquals(board.getState(2, 2), copy.getState(2, 2));
    }

    @Test
    void copy_sameBoardState() {
        board.setState(0, 0, CellState.ALIVE);
        board.setState(0, 1, CellState.ALIVE);
        board.setState(0, 2, CellState.ALIVE);
        Board copy = board.copy();

        assertEquals(board.getWidth(), copy.getWidth());
        assertEquals(board.getHeight(), copy.getHeight());
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                assertEquals(board.getState(x, y), copy.getState(x, y));
            }
        }
    }

    @Test
    void setState_topLeftBoundary() {
        board.setState(0, 0, CellState.ALIVE);

        assertEquals(CellState.ALIVE, board.getState(0, 0));
    }

    @Test
    void setState_bottomRightBoundary() {
        board.setState(4, 2, CellState.ALIVE);

        assertEquals(CellState.ALIVE, board.getState(4, 2));
    }

    @Test
    void setState_outsideLeftBoundary() {
        board.setState(-1, 0, CellState.ALIVE);
    }

    @Test
    void setState_outsideTopBoundary() {
        board.setState(0, -1, CellState.ALIVE);
    }

    @Test
    void setState_outsideRightBoundary() {
        board.setState(5, 0, CellState.ALIVE);
    }

    @Test
    void setState_outsideBottomBoundary() {
        board.setState(0, 3, CellState.ALIVE);
    }

    @Test
    void getState_outsideTopBoundary() {
        board.setState(0, -1, CellState.ALIVE);

        assertEquals(CellState.DEAD, board.getState(0, -1));
    }

    @Test
    void getState_outsideLeftBoundary() {
        board.setState(-1, 0, CellState.ALIVE);

        assertEquals(CellState.DEAD, board.getState(-1, 0));
    }

    @Test
    void getState_outsideBottomBoundary() {
        board.setState(0, 3, CellState.ALIVE);

        assertEquals(CellState.DEAD, board.getState(0, 3));
    }

    @Test
    void getState_outsideRightBoundary() {
        board.setState(5, 0, CellState.ALIVE);

        assertEquals(CellState.DEAD, board.getState(5, 0));
    }
}