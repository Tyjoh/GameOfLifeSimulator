package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.event.ToolActionEvent;

public interface EditorTool {

    String name();

    void handle(ToolActionEvent actionEvent);

}
