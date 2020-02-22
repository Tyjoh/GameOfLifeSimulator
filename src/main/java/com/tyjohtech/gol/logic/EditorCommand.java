package com.tyjohtech.gol.logic;

import com.tyjohtech.gol.command.Command;
import com.tyjohtech.gol.state.EditorState;

public interface EditorCommand extends Command<EditorState> {
    @Override
    void execute(EditorState editorState);
}
