package com.tyjohtech.gol.model.board;

public class BoardRegion {

    public interface Iterator {
        void iterate(int x, int y, int rx, int ry);
    }

    private CellPosition topLeft;
    private CellPosition bottomRight;

    public BoardRegion(CellPosition pos1, CellPosition pos2) {
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

    public int getWidth() {
        return bottomRight.getX() - topLeft.getX();
    }

    public int getHeight() {
        return bottomRight.getY() - topLeft.getY();
    }

    public boolean contains(CellPosition pos) {
        return pos.getX() >= topLeft.getX() && pos.getX() <= bottomRight.getX()
                && pos.getY() >= topLeft.getY() && pos.getY() <= bottomRight.getY();
    }

    public void iterate(Iterator iterator) {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                iterator.iterate(this.getTopLeft().getX() + x, this.getTopLeft().getY() + y, x, y);
            }
        }
    }
}
