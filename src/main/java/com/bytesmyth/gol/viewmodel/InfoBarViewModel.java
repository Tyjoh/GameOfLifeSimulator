package com.bytesmyth.gol.viewmodel;

import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.model.CellState;
import com.bytesmyth.gol.util.Property;

public class InfoBarViewModel {

    private Property<CellState> currentDrawMode = new Property<>(CellState.ALIVE);
    private Property<CellPosition> cursorPosition = new Property<>();

    public Property<CellState> getCurrentDrawMode() {
        return currentDrawMode;
    }

    public Property<CellPosition> getCursorPosition() {
        return cursorPosition;
    }
}
