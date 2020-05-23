package com.tyjohtech.gol.components.editor.command;

import com.tyjohtech.app.command.Command;
import com.tyjohtech.gol.components.editor.state.EditorState;

public interface EditorCommand extends Command<EditorState> {
    @Override
    void execute(EditorState editorState);

    @Override
    default Class<EditorState> getStateClass() {
        return EditorState.class;
    }
}
