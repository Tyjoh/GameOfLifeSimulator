package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.util.command.Command;

public interface EditorTool {

    String name();

    Command createCommand();

}
