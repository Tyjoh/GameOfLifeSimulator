package com.tyjohtech.gol.model;

public class CellRegion {

    private CellPosition topLeft;
    private CellPosition bottomRight;

    public CellRegion(CellPosition pos1, CellPosition pos2) {
        int left = Math.min(pos1.getX(), pos2.getX());
        int right = Math.max(pos1.getX(), pos2.getX());
        int top = Math.min(pos1.getY(), pos2.getY());
        int bottom = Math.max(pos1.getY(), pos2.getY());
        this.topLeft = CellPosition.of(left, top);
        this.bottomRight = CellPosition.of(right, bottom);
    }

    public CellPosition getTopLeft() {
        return topLeft;
    }

    public CellPosition getBottomRight() {
        return bottomRight;
    }
}
