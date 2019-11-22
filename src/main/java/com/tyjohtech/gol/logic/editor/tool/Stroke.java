package com.tyjohtech.gol.logic.editor.tool;

import java.util.HashSet;

public class Stroke extends HashSet<StrokePoint> {
    @Override
    public Stroke clone() {
        return (Stroke) super.clone();
    }
}
