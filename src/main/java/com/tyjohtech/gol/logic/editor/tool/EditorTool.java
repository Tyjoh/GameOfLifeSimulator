package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.BoardEditor;
import com.tyjohtech.gol.logic.editor.BoardEvent;

public interface EditorTool {
    void handle(BoardEvent boardEvent, BoardEditor state);
}
