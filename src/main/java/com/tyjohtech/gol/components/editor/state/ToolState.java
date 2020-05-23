package com.tyjohtech.gol.components.editor.state;

import com.tyjohtech.app.property.AbstractObservable;
import com.tyjohtech.gol.model.CellPosition;
import com.tyjohtech.gol.model.CellState;

public class ToolState extends AbstractObservable<ToolState> {

    private CellPosition cellPosition;
    private CellState mode;
    private String currentTool;

    public CellPosition getCursorPosition() {
        return cellPosition;
    }

    public CellState getDrawMode() {
        return mode;
    }

    public String getCurrentTool() {
        return currentTool;
    }

    public void setCursorPosition(CellPosition cellPosition) {
        this.cellPosition = cellPosition;
        this.notifyListeners(this);
    }

    public void setDrawMode(CellState mode) {
        this.mode = mode;
        this.notifyListeners(this);
    }

    public void setCurrentTool(String currentTool) {
        this.currentTool = currentTool;
        this.notifyListeners(this);
    }
}
