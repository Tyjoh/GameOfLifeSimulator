package com.tyjohtech.gol.model;

public class BoundedBoard implements Board {

    private int width;
    private int height;
    private CellState[][] board;

    public BoundedBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new CellState[width][height];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                setState(x, y, CellState.DEAD);
            }
        }
    }

    @Override
    public BoundedBoard copy() {
        BoundedBoard copy = new BoundedBoard(this.width, this.height);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                copy.setState(x, y, this.getState(x, y));
            }
        }

        return copy;
    }

    @Override
    public void set(Board board) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                setState(x, y, board.getState(x, y));
            }
        }
    }

    @Override
    public CellState getState(int x, int y) {
        if (x < 0 || x >= this.width) {
            return CellState.DEAD;
        }

        if (y < 0 || y >= this.height) {
            return CellState.DEAD;
        }

        return this.board[x][y];
    }

    @Override
    public void setState(int x, int y, CellState cellState) {
        if (x < 0 || x >= this.width) {
            return;
        }

        if (y < 0 || y >= this.height) {
            return;
        }

        this.board[x][y] = cellState;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
