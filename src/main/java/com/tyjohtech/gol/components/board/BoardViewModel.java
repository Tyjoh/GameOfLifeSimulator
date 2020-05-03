package com.tyjohtech.gol.components.board;

import com.tyjohtech.app.property.Property;
import com.tyjohtech.gol.model.Board;
import com.tyjohtech.gol.model.CellPosition;

public class BoardViewModel {

    private Property<Board> board = new Property<>();
    private Property<CellPosition> cursorPosition = new Property<>();

    public Property<Board> getBoard() {
        return board;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }
}
