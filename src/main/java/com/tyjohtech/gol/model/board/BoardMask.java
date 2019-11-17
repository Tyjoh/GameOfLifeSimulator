package com.tyjohtech.gol.model.board;

public class BoardMask {

    private int width;
    private int height;
    private boolean[][] mask;

    public BoardMask(int width, int height) {
        this.width = width;
        this.height = height;
        mask = new boolean[width][height];
    }

    public void set(int x, int y, boolean val) {
        mask[x][y] = val;
    }

    public boolean isSet(int x, int y) {
        if (x < 0 || x >= width) {
            return false;
        }

        if (y < 0 || y >= height) {
            return false;
        }

        return mask[x][y];
    }

}
