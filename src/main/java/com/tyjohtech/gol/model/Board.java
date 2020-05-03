package com.tyjohtech.gol.model;

public interface Board {

    Board copy();

    CellState getState(int x, int y);

    void set(Board board);

    void setState(int x, int y, CellState cellState);

    int getWidth();

    int getHeight();

}
