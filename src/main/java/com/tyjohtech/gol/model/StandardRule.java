package com.tyjohtech.gol.model;

public class StandardRule implements SimulationRule {

    @Override
    public CellState getNextState(int x, int y, Board board) {
        int aliveNeighbours = countAliveNeighbours(x, y, board);

        if (board.getState(x, y) == CellState.ALIVE) {
            if (aliveNeighbours < 2) {
                return CellState.DEAD;
            } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return CellState.ALIVE;
            } else if (aliveNeighbours > 3) {
                return CellState.DEAD;
            }
        } else {
            if (aliveNeighbours == 3) {
                return CellState.ALIVE;
            }
        }

        return CellState.DEAD;
    }

    private int countAliveNeighbours(int x, int y, Board board) {
        int count = 0;

        count += countCell(x - 1, y - 1, board);
        count += countCell(x, y - 1, board);
        count += countCell(x + 1, y - 1, board);

        count += countCell(x - 1, y, board);
        count += countCell(x + 1, y, board);

        count += countCell(x - 1, y + 1, board);
        count += countCell(x, y + 1, board);
        count += countCell(x + 1, y + 1, board);

        return count;
    }

    private int countCell(int x, int y, Board board) {
        if (board.getState(x, y) == CellState.ALIVE) {
            return 1;
        } else {
            return 0;
        }
    }
}
