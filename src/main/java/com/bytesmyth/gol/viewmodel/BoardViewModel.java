package com.bytesmyth.gol.viewmodel;

import com.bytesmyth.gol.model.Board;
import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.util.Property;

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
