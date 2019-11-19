package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.util.event.Event;

public class BrushConfigEvent implements Event {
    private int brushSize;

    public BrushConfigEvent(int brushSize) {
        this.brushSize = brushSize;
    }

    public int getBrushSize() {
        return brushSize;
    }
}
