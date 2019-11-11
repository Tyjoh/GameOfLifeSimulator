package com.tyjohtech.gol.editor;

import com.tyjohtech.gol.model.CellPosition;

import java.util.Objects;

public class BoardEvent {

    private BoardEventType boardEventType;
    private CellPosition position;

    private BoardEvent(BoardEventType boardEventType, CellPosition position) {
        this.boardEventType = boardEventType;
        this.position = position;
    }

    public static BoardEvent hover(CellPosition cellPosition) {
        return new BoardEvent(BoardEventType.HOVER, cellPosition);
    }

    public static BoardEvent release(CellPosition cellPosition) {
        return new BoardEvent(BoardEventType.RELEASE, cellPosition);
    }

    public static BoardEvent drag(CellPosition cellPosition) {
        return new BoardEvent(BoardEventType.DRAG, cellPosition);
    }

    public BoardEventType getBoardEventType() {
        return boardEventType;
    }

    public CellPosition getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardEvent that = (BoardEvent) o;
        return boardEventType == that.boardEventType &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardEventType, position);
    }
}
