package com.bytesmyth.gol.components.infobar;

import com.bytesmyth.app.observable.Property;
import com.bytesmyth.gol.model.CellPosition;
import com.bytesmyth.gol.model.CellState;

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
