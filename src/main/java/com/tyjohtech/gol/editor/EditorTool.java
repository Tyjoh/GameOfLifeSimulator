package com.tyjohtech.gol.editor;

public interface EditorTool {
    void handle(BoardEvent boardEvent, BoardEditor state);
}
