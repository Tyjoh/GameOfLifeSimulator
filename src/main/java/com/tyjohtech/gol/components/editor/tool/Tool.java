package com.tyjohtech.gol.components.editor.tool;

public interface Tool {

    String getName();

    void begin();

    void moved();

    void end();

}
