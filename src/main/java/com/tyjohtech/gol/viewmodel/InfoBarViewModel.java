package com.tyjohtech.gol.viewmodel;

import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;
import com.tyjohtech.gol.util.Property;

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
