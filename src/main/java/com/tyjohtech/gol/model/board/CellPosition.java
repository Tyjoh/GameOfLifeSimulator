package com.tyjohtech.gol.model.board;

import java.util.Objects;

public class CellPosition {
    private int x;
    private int y;

    private CellPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static CellPosition of(int x, int y) {
        return new CellPosition(x, y);
    }

    public static CellPosition of(CellPosition pos) {
        return new CellPosition(pos.x, pos.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
