package com.tyjohtech.gol.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardRuleTest {

    private StandardRule rule;
    private Board board;

    @BeforeEach
    void setUp() {
        this.rule = new StandardRule();
        this.board = new BoundedBoard(5, 5);
        this.board.setState(0, 0, CellState.ALIVE);
        this.board.setState(2, 0, CellState.ALIVE);
        this.board.setState(3, 0, CellState.ALIVE);
        this.board.setState(0, 1, CellState.ALIVE);
        this.board.setState(0, 3, CellState.ALIVE);
        this.board.setState(0, 4, CellState.ALIVE);
        this.board.setState(1, 4, CellState.ALIVE);
        this.board.setState(3, 4, CellState.ALIVE);
        this.board.setState(4, 1, CellState.ALIVE);
        this.board.setState(4, 2, CellState.ALIVE);
        this.board.setState(4, 3, CellState.ALIVE);
    }

    @Test
    void getNextState_deathByUnderpopulation_noNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.DEAD, nextState);
    }

    @Test
    void getNextState_deathByUnderpopulation_oneNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);
        this.board.setState(1, 2, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.DEAD, nextState);
    }

    @Test
    void getNextState_livesToNextGeneration_twoNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);
        this.board.setState(3, 2, CellState.ALIVE);
        this.board.setState(1, 1, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.ALIVE, nextState);
    }

    @Test
    void getNextState_livesToNextGeneration_threeNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);
        this.board.setState(2, 3, CellState.ALIVE);
        this.board.setState(3, 3, CellState.ALIVE);
        this.board.setState(3, 2, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.ALIVE, nextState);
    }

    @Test
    void getNextState_diesByOverpopulation_fourNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);
        this.board.setState(2, 3, CellState.ALIVE);
        this.board.setState(3, 3, CellState.ALIVE);
        this.board.setState(3, 2, CellState.ALIVE);
        this.board.setState(1, 3, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.DEAD, nextState);
    }

    @Test
    void getNextState_diesByOverpopulation_eightNeighbours() {
        this.board.setState(2, 2, CellState.ALIVE);
        this.board.setState(1, 1, CellState.ALIVE);
        this.board.setState(2, 1, CellState.ALIVE);
        this.board.setState(3, 1, CellState.ALIVE);
        this.board.setState(3, 2, CellState.ALIVE);
        this.board.setState(3, 3, CellState.ALIVE);
        this.board.setState(2, 3, CellState.ALIVE);
        this.board.setState(1, 3, CellState.ALIVE);
        this.board.setState(1, 2, CellState.ALIVE);

        CellState nextState = rule.getNextState(2, 2, board);
        assertEquals(CellState.DEAD, nextState);
    }
}